package com.example.taskFlow.dto;

import lombok.Data;

@Data
public class TagDTO {
    private Long id;
    private String name;
    private Long taskId; 
}
