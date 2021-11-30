package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * 部门实体类
 *
 * @author fangyuan
 * @date 2021/11/30
 */
@Data
@NoArgsConstructor
public class Department {

    public Department(String id) {
        this.id = id;
    }

    private String id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门电话
     */
    private String tel;

    /**
     * 部门成员
     */
    private Set<User> users;
}
