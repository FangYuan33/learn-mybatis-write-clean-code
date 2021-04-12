import dao.DepartmentDao;
import dao.impl.DepartmentDaoImpl;
import entity.Department;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisApplication2 {
    public static void main(String[] args) throws IOException {
        InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xml);

//        DepartmentDaoImpl departmentDao = new DepartmentDaoImpl(sqlSessionFactory);
//        List<Department> list = departmentDao.findAll();
//        list.forEach(System.out::println);
//        Department department = departmentDao.findById("18ec781fbefd727923b0d35740b177ab");
//        System.out.println(department);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        DepartmentDao mapper = sqlSession.getMapper(DepartmentDao.class);
        Department department = mapper.findById("18ec781fbefd727923b0d35740b177ab");
        System.out.println(department);
    }
}
