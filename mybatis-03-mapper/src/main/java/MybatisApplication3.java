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
        //开启二级缓存需要在对应的xml文件中添加<cache/>的标签，并且实体类需要实现Serializable
        //flushCathe标签清除的是全局一级缓存和对应的namespace下的二级缓存
        //因为清除缓存后，我们再进行查询的时候没有在控制台输出查询语句，且显示了 Cache Hit Ratio
        InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(xml);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        DepartmentDao departmentDao = sqlSession.getMapper(DepartmentDao.class);
        Department department = departmentDao.findById("18ec781fbefd727923b0d35740b177ab");
        System.out.println(department);
        System.out.println("----------------------完成第一次查询Department");
        Department department1 = departmentDao.findById("18ec781fbefd727923b0d35740b177ab");
        //true 使用了缓存
        System.out.println("department == department1 ???" + (department == department1));
        sqlSession.close();

        System.out.println("---------------关闭sqlSession，开启新的sqlSession1，再执行查询Department，看是否使用了二级缓存");
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        DepartmentDao departmentDao1 = sqlSession1.getMapper(DepartmentDao.class);
        Department department2 = departmentDao1.findById("18ec781fbefd727923b0d35740b177ab");
        System.out.println(department2);

        UserDao userDao = sqlSession1.getMapper(UserDao.class);
        userDao.cleanCathe();
        System.out.println("---------清除缓存");
        Department department3 = departmentDao1.findById("18ec781fbefd727923b0d35740b177ab");
    }
}
