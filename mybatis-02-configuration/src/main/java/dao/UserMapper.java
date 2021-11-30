package dao;

import entity.User;

import java.util.List;

public interface UserMapper {
    List<User> findAll();

    List<User> lazyFindAll();

    List<User> findUserByDepartmentId(String departmentId);

    int cleanCathe();

    /**
     * 使用DepartmentTypeHandler使Departments字段有值
     */
    List<User> findAllUseTypeHandler();

    void saveUser(User user);
}
