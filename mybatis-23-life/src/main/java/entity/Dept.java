package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 部门实体类
 *
 * @author fangyuan
 */
@Data
@NoArgsConstructor
public class Dept {

    public Dept(Integer id) {
        this.id = id;
    }

    public Dept(String name) {
        this.name = name;
    }

    public Dept(String name, String tel) {
        this.name = name;
        this.tel = tel;
    }

    public Dept(Integer id, String name, String tel) {
        this.id = id;
        this.name = name;
        this.tel = tel;
    }

    private Integer id;

    private String name;

    private String tel;
}
