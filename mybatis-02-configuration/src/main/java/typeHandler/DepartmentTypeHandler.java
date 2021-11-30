package typeHandler;

import entity.Department;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Department类型处理器
 *
 * @author fangyuan
 * @date 2021/11/30
 */
public class DepartmentTypeHandler implements TypeHandler<Department> {
    /**
     * 新增user的时候这里它会将Department对象转换成department_id
     */
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Department department, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, department.getId());
    }

    @Override
    public Department getResult(ResultSet resultSet, String columnName) throws SQLException {
        Department department = new Department();
        // 这里set的值就是最后返回给User中Department的值，如果在这里想要获取其他值的话，查一遍库？
        department.setId(resultSet.getString(columnName));

        return department;
    }

    @Override
    public Department getResult(ResultSet resultSet, int columnIndex) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getString(columnIndex));

        return department;
    }

    @Override
    public Department getResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        Department department = new Department();
        department.setId(callableStatement.getString(columnIndex));

        return department;
    }
}
