import dao.UserMapper;
import entity.User;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

/**
 * Choose when otherwise
 * 只能在这些条件中选择一个
 * 类似
 * if() {
 *     ...
 * } else if() {
 *     ...
 * } else {
 *     ...
 * }
 *
 * @author fangyuan
 */
public class TestChooseWhen {
    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = MybatisApplication.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        userMapper.findOneByOneCondition(new User("09ec5fcea620c168936deee53a9cdcfb", "老狗", 18));
    }
}
