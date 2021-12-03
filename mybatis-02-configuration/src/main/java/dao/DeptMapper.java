package dao;

import entity.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DeptMapper {

    int insert(Dept dept);

    /**
     * 练习使用foreach
     */
    List<Dept> listInIds(@Param("ids") List<Integer> ids);

    /**
     * 使用bind标签
     */
    List<Dept> listLikeName(Dept dept);

    /**
     * 练习使用set标签
     */
    int updateByEntity(Dept dept);

    /**
     * 学习使用map更新对象
     */
    int updateByMap(Map<String, Object> map);
}
