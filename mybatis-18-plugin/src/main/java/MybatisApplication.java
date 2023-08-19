
import dao.DeptMapper;
import dao.UserMapper;
import entity.Dept;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;


public class MybatisApplication {
    public static void main(String[] args) throws IOException, InterruptedException {
        SqlSession sqlSession = getSqlSession();
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);


        for (int i = 0; i < 100; i++) {
            try {
                Dept dept = new Dept(i, "运配", "12345678910");
                deptMapper.updateByEntity(dept);
            } catch (Exception e) {

            }
        }

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    Dept dept = new Dept(i, "运配", "12345678910");
                    deptMapper.updateByEntity(dept);
                } catch (Exception e) {

                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    Dept dept = new Dept(i, "运配", "12345678910");
                    deptMapper.updateByEntity(dept);
                } catch (Exception e) {

                }
            }
        }).start();

        Thread.sleep(5000);


//        userMapper.findAll();

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
