package dao;

import entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    List<User> findAll();

    List<User> lazyFindAll();

    List<User> findUserByDepartmentId(String departmentId);

    int cleanCathe();

    List<User> getUserByNameOrAge(@Param("name") String name, @Param("age") Integer age);

    List<User> testChooseWhenOtherwise(@Param("id") String id, @Param("name") String name, @Param("age") Integer age);

    List<User> testForeach(@Param("ages") List<Integer> ages);

    List<User> testBind(@Param("id") String id);

    int testSet(User user);
}
