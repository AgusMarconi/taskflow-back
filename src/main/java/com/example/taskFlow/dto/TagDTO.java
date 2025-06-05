package com.example.taskFlow.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TagDTO {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
