package com.example.taskFlow.dto;

import com.example.taskFlow.enumeration.Status;
import com.example.taskFlow.enumeration.Priority;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private LocalDateTime finished_at;
    private Long userId; 
}
