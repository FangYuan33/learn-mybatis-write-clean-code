package com.mybatis.book.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户
 *
 * @author FangYuan
 * @since 2024-02-06 17:46:24
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    public User(@Param("id") String id, @Param("name") String name, @Param("age") Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    private String id;

    private String name;

    private Integer age;

    private LocalDateTime birthday;

    private Department department;
}
