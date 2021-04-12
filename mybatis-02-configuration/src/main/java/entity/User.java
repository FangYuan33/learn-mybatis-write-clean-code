package entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private String id;
    private String name;
    private Integer age;
    private LocalDateTime birthday;
    private Department department;
}
