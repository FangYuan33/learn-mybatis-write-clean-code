package dao;

import entity.User;

import java.util.List;

public interface UserMapper {
    List<User> findAll();

    /**
     * 测试带构造器的ResultMap
     */
    List<User> findAllWithConstructorResultMap();

    /**
     * 测试resultMap的 extends标签
     */
    List<User> findAllWithExtendsResultMap();

    List<User> lazyFindAll();

    List<User> findUserByDepartmentId(String departmentId);

    int cleanCathe();

    /**
     * 使用DepartmentTypeHandler使Departments字段有值
     */
    List<User> findAllUseTypeHandler();

    void saveUser(User user);

    List<User> findAllWithDiscriminator();

    /**
     * 测试 choose when otherwise 标签
     */
    User findOneByOneCondition(User user);
}
