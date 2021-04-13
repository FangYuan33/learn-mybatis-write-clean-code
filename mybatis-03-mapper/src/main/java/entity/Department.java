package entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class Department implements Serializable {
    private static final long serialVersionUID = 1234567890L;

    private String id;
    private String name;
    private String tel;
    private Set<User> users;
}
