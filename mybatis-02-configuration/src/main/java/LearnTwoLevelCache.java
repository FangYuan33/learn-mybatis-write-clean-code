import dao.DepartmentMapper;
import dao.UserMapper;
import entity.Department;
import entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 学习二级缓存相关
 *
 * 博客思路
 * 1. 介绍2级缓存的生效机制
 * 2. 说cathe标签的时候介绍readOnly的妙用
 * 3. 分析cathe源码，看装饰者模式
 * 4. 提交后才能获取脏数据的原因
 */
public class LearnTwoLevelCache {
    public static void main(String[] args) throws IOException {
        InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 开启二级缓存需要在同一个SqlSessionFactory下，二级缓存存在于 SqlSessionFactory 生命周期，如此才能命中二级缓存
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(xml);

        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
        DepartmentMapper departmentMapper1 = sqlSession1.getMapper(DepartmentMapper.class);

        System.out.println("----------department第一次查询 ↓------------");
        List<Department> departments1 = departmentMapper1.findAll();
        System.out.println("----------user第一次查询 ↓------------");
        List<User> users1 = userMapper1.findAll();

        sqlSession1.commit();

        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        DepartmentMapper departmentMapper2 = sqlSession2.getMapper(DepartmentMapper.class);

        System.out.println("----------department第二次查询 ↓------------");
        List<Department> departments2 = departmentMapper2.findAll();
        System.out.println("----------user第二次查询 ↓------------");
        List<User> users2 = userMapper2.findAll();


        sqlSession1.close();
        sqlSession2.close();
    }
}
