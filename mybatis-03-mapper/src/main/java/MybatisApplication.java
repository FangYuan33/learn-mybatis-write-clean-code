import dao.UserDao;
import entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class MybatisApplication {
    public static void main(String[] args) throws IOException {
        InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(xml);

        //测试使用trim标签的修建功能
        //prefix 添加的前缀（where） prefixOverrides 帮我们剪去的（and）
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> users = userDao.getUserByNameOrAge(null, 18);
        //users.forEach(System.out::println);

        //测试使用choose when otherwise
        //代表着是 if-else if-else 只会走其中一个条件
        List<User> userList = userDao.testChooseWhenOtherwise(null, null, null);
        //userList.forEach(System.out::println);

        //测试使用foreach age作为一个集合出入进去，进行in条件匹配
        List<User> foreach = userDao.testForeach(Arrays.asList(18, 30));
        //foreach.forEach(System.out::println);

        //_parameter.id _parameter这个参数比较特别，可以通过它来获取上下文参数
        //<bind> 标签用来我们绑定一个新的变量
        List<User> testBind = userDao.testBind("");
        //testBind.forEach(System.out::println);

        //set 标签使用 它帮我们去掉的是最后面的逗号
        //trim 标签同样可以这么用 <trim prefix="SET" suffixOverrides=",">
        User update = new User();
        update.setId("09ec5fcea620c168936deee53a9cdcfb");
        update.setName("XiaoWang");
        update.setAge(23);
        userDao.testSet(update);
        //System.out.println(userDao.getUserByNameOrAge("XiaoWang", null));


    }
}
