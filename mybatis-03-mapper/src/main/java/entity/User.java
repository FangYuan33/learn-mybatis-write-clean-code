package entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 123L;

    private String id;
    private String name;
    private Integer age;
    private LocalDateTime birthday;
    private Department department;
}
