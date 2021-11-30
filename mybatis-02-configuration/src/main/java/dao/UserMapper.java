package dao;

import entity.User;

import java.util.List;

public interface UserMapper {
    List<User> findAll();

    List<User> lazyFindAll();

    List<User> findUserByDepartmentId(String departmentId);

    int cleanCathe();
}
