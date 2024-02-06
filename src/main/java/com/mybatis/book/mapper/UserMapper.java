package com.mybatis.book.mapper;

import com.mybatis.book.entity.User;

import java.util.List;

public interface UserMapper {
    List<User> findAll();

    /**
     * 测试 choose when otherwise 标签
     */
    User findOneByOneCondition(User user);
}
