import dao.DepartmentMapper;
import dao.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 测试flushCache清除所有一级缓存和 当前namespace下的二级缓存
 *
 * @author fangyuan
 */
public class TestMyBatistaCache {

    public static void main(String[] args) throws IOException {
        InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 开启二级缓存需要在同一个SqlSessionFactory下，二级缓存存在于 SqlSessionFactory 生命周期，如此才能命中二级缓存
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(xml);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
        System.out.println("----------department第一次查询 ↓------------");
        departmentMapper.findById("18ec781fbefd727923b0d35740b177ab");
        System.out.println("----------department一级缓存生效，控制台看不见SQL ↓------------");
        departmentMapper.findById("18ec781fbefd727923b0d35740b177ab");

        System.out.println("---------- 关闭SQLSession，使department二级缓存生效 ------------");
        sqlSession.close();

        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        DepartmentMapper departmentMapper2 = sqlSession2.getMapper(DepartmentMapper.class);
        System.out.println("----------department二级缓存生效，控制台看不见SQL ↓------------");
        departmentMapper2.findById("18ec781fbefd727923b0d35740b177ab");

        UserMapper userMapper = sqlSession2.getMapper(UserMapper.class);
        System.out.println("----------user第一次查询 ↓------------");
        userMapper.findAll();
        System.out.println("----------user一级缓存生效，控制台看不见SQL ↓------------");
        userMapper.findAll();

        System.out.println("----------清除缓存，使当前department二级缓存失效，所有department和user一级缓存失效 ↓------------");
        departmentMapper2.cleanCathe();

        System.out.println("----------user一级缓存失效，控制台出现SQL ↓------------");
        userMapper.findAll();
        System.out.println("----------department一级和二级缓存失效，控制台出现SQL ↓------------");
        departmentMapper2.findById("18ec781fbefd727923b0d35740b177ab");
    }

}
