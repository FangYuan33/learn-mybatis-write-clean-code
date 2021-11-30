package entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class User {
    private String id;

    private String name;

    private Integer age;

    private LocalDateTime birthday;

    private Department department;
}
