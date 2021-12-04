package dao;

import dao.providers.DeptMapperProvider;
import entity.Dept;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;

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

    /**
     * 学习使用SelectProvider
     */
    @SelectProvider(type = DeptMapperProvider.class, method = "findAll")
    List<Dept> findAll();

    /**
     * 复杂SQL拼接
     */
    @SelectProvider(type = DeptMapperProvider.class, method = "findByEntity")
    List<Dept> findByEntity(Dept dept);
}
