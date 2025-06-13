package com.example.taskFlow.service.imp;

import org.springframework.stereotype.Service;
import com.example.taskFlow.service.TaskService;
import com.example.taskFlow.repository.TaskRepository;
import com.example.taskFlow.entity.Task;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TaskServiceImp implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public List<Task> findByTagId(Long tagId) {
        return taskRepository.findByTagsId(tagId);
    }

    @Override
    public List<Task> findByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    @Override
    public List<Task> findByDueDateBefore(LocalDateTime date) {
        return taskRepository.findByDueDateBefore(date);
    }

    @Override
    public List<Task> findByDueDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return taskRepository.findByDueDateBetween(startDate, endDate);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task update(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
