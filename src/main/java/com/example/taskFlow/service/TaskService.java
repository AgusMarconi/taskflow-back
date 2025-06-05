package com.example.taskFlow.service;

import com.example.taskFlow.entity.Task;
import java.util.List;

public interface TaskService {
    Task findById(Long id);
    List<Task> findByTagId(Long tagId);
    List<Task> findByUserId(Long userId);
    List<Task> findAll();
    Task save(Task task);
    Task update(Task task);
    void deleteById(Long id);
}
