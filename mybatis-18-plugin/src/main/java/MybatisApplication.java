
import dao.DeptMapper;
import entity.Dept;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;


public class MybatisApplication {
    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = getSqlSession();
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);

        Dept dept = new Dept(1, "运配", "12345678910");
        deptMapper.updateByEntity(dept);

//        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 获取SqlSession
     */
    public static SqlSession getSqlSession() throws IOException {
        InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(xml);

        return sqlSessionFactory.openSession();
    }
}
