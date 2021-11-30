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
import java.util.UUID;

public class MybatisApplication {
    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = getSqlSession();

        // 1. 查询部门信息
        DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
//        departmentMapper.findAll().forEach(System.out::println);

        // 2. 查询用户信息 多对一
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.findAll().forEach(System.out::println);

        // 3. 延迟加载 在resultMap在此调用其他SQL
//        userMapper.lazyFindAll().forEach(System.out::println);

        // 4. 测试typeHandler
//        userMapper.findAllUseTypeHandler().forEach(System.out::println);
        // 新增用户时，这里会将Department对象转换成department_id，虽然我们这里写的是Department对象形式
//        userMapper.saveUser(new User().setId(UUID.randomUUID().toString().replaceAll("-", ""))
//                .setName("方圆")
//                .setDepartment(new Department("ee0e342201004c1721e69a99ac0dc0df")));

        sqlSession.commit();
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
