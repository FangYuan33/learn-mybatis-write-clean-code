import dao.DepartmentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisApplication {
    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = getSqlSession();

        DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
        departmentMapper.findAll().forEach(System.out::println);

        sqlSession.close();
    }

    /**
     * 获取SqlSession
     */
    private static SqlSession getSqlSession() throws IOException {
        InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(xml);
        return sqlSessionFactory.openSession();
    }
}
