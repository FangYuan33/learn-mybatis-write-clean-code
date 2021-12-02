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

    public Dept(String name, String tel) {
        this.name = name;
        this.tel = tel;
    }

    private Integer id;

    private String name;

    private String tel;
}
