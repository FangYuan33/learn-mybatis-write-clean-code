package dao;

import entity.Dept;

import java.util.List;

public interface DeptMapper {

    /**
     * 练习使用set标签
     */
    int updateByEntity(Dept dept);

    List<Dept> findAll(Dept dept);
}
