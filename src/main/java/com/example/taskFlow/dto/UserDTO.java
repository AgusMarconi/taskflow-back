package com.example.taskFlow.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
