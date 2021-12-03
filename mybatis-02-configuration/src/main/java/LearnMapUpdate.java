import dao.DeptMapper;
import entity.Dept;
import net.sf.cglib.beans.BeanMap;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 学习使用map作为参数写update语句，实在是太太太方便了！
 *
 * @author fangyuan
 */
public class LearnMapUpdate {
    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = MybatisApplication.getSqlSession();
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);

        // 这里定义一个map是需要更新的字段
        Dept dept = new Dept();
        dept.setName("交电费的南方电网");
        BeanMap beanMap = BeanMap.create(dept);

        // 这里又定义了一个map的是封装上边的map和id 根据id进行匹配，根据map进行赋值
        Map<String, Object> map = new HashMap<>();
        map.put("beanMap", beanMap);
        map.put("id", 4);
        deptMapper.updateByMap(map);
    }
}
