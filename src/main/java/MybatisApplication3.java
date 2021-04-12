import dao.DepartmentDao;
import dao.UserDao;
import entity.Department;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisApplication3 {
    public static void main(String[] args) throws IOException {
        InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(xml);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserDao userDao = sqlSession.getMapper(UserDao.class);
        userDao.findAll().forEach(System.out::println);
        userDao.lazyFindAll().forEach(System.out::println);

        DepartmentDao departmentDao = sqlSession.getMapper(DepartmentDao.class);
        Department department = departmentDao.findById("ee0e342201004c1721e69a99ac0dc0df");
        System.out.println(department);
    }
}
