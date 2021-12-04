package dao.providers;

import entity.Dept;
import org.apache.ibatis.jdbc.SQL;

/**
 * 学习使用Provider
 *
 * @author fangyuan
 */
public class DeptMapperProvider {

    /**
     * 注意这个方法的返回值是sql的String类型
     */
    public String findAll() {
        SQL sql = new SQL();
        sql.SELECT("*").FROM("dept");

        return sql.toString();
    }

    /**
     * where条件的拼接
     */
    public String findByEntity(Dept dept) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM("dept");
        if (dept.getName() != null) {
            sql.WHERE("name like concat('%', #{name}, '%')");
        }
        if (dept.getId() != null) {
            sql.AND().WHERE("id = #{id}");
        }

        return sql.toString();
    }
}
