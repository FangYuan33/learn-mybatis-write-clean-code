import dao.DeptMapper;
import entity.Dept;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

public class TestProvider {
    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = MybatisApplication.getSqlSession();
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);

//        System.out.println(deptMapper.findAll());

        System.out.println(deptMapper.findByEntity(new Dept("东")));
    }
}
