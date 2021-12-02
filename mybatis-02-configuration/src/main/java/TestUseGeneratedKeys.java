import dao.DeptMapper;
import entity.Department;
import entity.Dept;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

/**
 * 你不是总想着在修改完对象之后要它的id值吗？
 *
 * @author fangyuan
 */
public class TestUseGeneratedKeys {
    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = MybatisApplication.getSqlSession();

        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = new Dept("京东", "1234567");
        deptMapper.insert(dept);
        sqlSession.commit();

//        在这里可以发现，主键值被成功赋值了
        System.out.println(dept);
    }
}
