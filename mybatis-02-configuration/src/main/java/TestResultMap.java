import dao.UserMapper;
import entity.User;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

/**
 * xml标签中的重中之重 resultMap
 *
 * @author fangyuan
 */
public class TestResultMap {
    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = MybatisApplication.getSqlSession();

        // 测试带constructor标签的resultMap
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//        List<User> users = userMapper.findAllWithConstructorResultMap();
//        users.forEach(System.out::println);

        // 测试resultMap的 extends标签
        List<User> users1 = userMapper.findAllWithExtendsResultMap();
//        users1.forEach(System.out::println);

        // 测试Discriminator，根据条件值的不同，返回不同的resultMap
        userMapper.findAllWithDiscriminator().forEach(System.out::println);
    }
}
