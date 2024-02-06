package com.mybatis.book.mapper;

import com.mybatis.book.entity.Department;

import java.util.List;

public interface DepartmentMapper {
    List<Department> findAll();

    Department findById(String id);
}
