package com.example.taskFlow.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.taskFlow.service.TaskService;
import com.example.taskFlow.entity.Task;
import com.example.taskFlow.dto.TaskDTO;
import com.example.taskFlow.dto.TagDTO;
import com.example.taskFlow.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import com.example.taskFlow.entity.User;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController extends BaseController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<TaskDTO> tasks = taskService.findAll().stream()
                .map(task -> {
                    TaskDTO dto = new TaskDTO();
                    dto.setId(task.getId());
                    dto.setTitle(task.getTitle());
                    dto.setDescription(task.getDescription());
                    dto.setStatus(task.getStatus());
                    dto.setPriority(task.getPriority());
                    dto.setCreated_at(task.getCreated_at());
                    dto.setUpdated_at(task.getUpdated_at());
                    dto.setFinished_at(task.getFinished_at());
                    dto.setUserId(task.getUser() != null ? task.getUser().getId() : null);

                    if (task.getTags() != null) {
                        List<TagDTO> tagDTOs = task.getTags().stream()
                                .map(tag -> {
                                    TagDTO tagDTO = new TagDTO();
                                    tagDTO.setId(tag.getId());
                                    tagDTO.setName(tag.getName());
                                    return tagDTO;
                                })
                                .collect(Collectors.toList());
                        dto.setTags(tagDTOs);
                    } else {
                        dto.setTags(new ArrayList<>());
                    }

                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        Task task = taskService.findById(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }

        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setPriority(task.getPriority());
        dto.setCreated_at(task.getCreated_at());
        dto.setUpdated_at(task.getUpdated_at());
        dto.setFinished_at(task.getFinished_at());
        dto.setUserId(task.getUser() != null ? task.getUser().getId() : null);

        if (task.getTags() != null) {
            List<TagDTO> tagDTOs = task.getTags().stream()
                    .map(tag -> {
                        TagDTO tagDTO = new TagDTO();
                        tagDTO.setId(tag.getId());
                        tagDTO.setName(tag.getName());
                        return tagDTO;
                    })
                    .collect(Collectors.toList());
            dto.setTags(tagDTOs);
        } else {
            dto.setTags(new ArrayList<>());
        }

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        task.setPriority(taskDTO.getPriority());
        task.setCreated_at(taskDTO.getCreated_at());
        task.setUpdated_at(taskDTO.getUpdated_at());
        task.setFinished_at(taskDTO.getFinished_at());

        User user = userService.getCurrentUser();
        task.setUser(user);

        if (task.getTags() == null) {
            task.setTags(new ArrayList<>());
        }

        Task savedTask = taskService.save(task);

        TaskDTO savedDTO = new TaskDTO();
        savedDTO.setId(savedTask.getId());
        savedDTO.setTitle(savedTask.getTitle());
        savedDTO.setDescription(savedTask.getDescription());
        savedDTO.setStatus(savedTask.getStatus());
        savedDTO.setPriority(savedTask.getPriority());
        savedDTO.setCreated_at(savedTask.getCreated_at());
        savedDTO.setUpdated_at(savedTask.getUpdated_at());
        savedDTO.setFinished_at(savedTask.getFinished_at());
        savedDTO.setUserId(savedTask.getUser() != null ? savedTask.getUser().getId() : null);

        if (savedTask.getTags() != null) {
            List<TagDTO> tagDTOs = savedTask.getTags().stream()
                    .map(tag -> {
                        TagDTO tagDTO = new TagDTO();
                        tagDTO.setId(tag.getId());
                        tagDTO.setName(tag.getName());
                        return tagDTO;
                    })
                    .collect(Collectors.toList());
            savedDTO.setTags(tagDTOs);
        } else {
            savedDTO.setTags(new ArrayList<>());
        }

        return ResponseEntity.ok(savedDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        Task existingTask = taskService.findById(id);
        if (existingTask == null) {
            return ResponseEntity.notFound().build();
        }

        existingTask.setTitle(taskDTO.getTitle());
        existingTask.setDescription(taskDTO.getDescription());
        existingTask.setStatus(taskDTO.getStatus());
        existingTask.setPriority(taskDTO.getPriority());
        existingTask.setCreated_at(taskDTO.getCreated_at());
        existingTask.setUpdated_at(taskDTO.getUpdated_at());
        existingTask.setFinished_at(taskDTO.getFinished_at());

        if (existingTask.getTags() == null) {
            existingTask.setTags(new ArrayList<>());
        }

        Task updatedTask = taskService.update(existingTask);

        TaskDTO updatedDTO = new TaskDTO();
        updatedDTO.setId(updatedTask.getId());
        updatedDTO.setTitle(updatedTask.getTitle());
        updatedDTO.setDescription(updatedTask.getDescription());
        updatedDTO.setStatus(updatedTask.getStatus());
        updatedDTO.setPriority(updatedTask.getPriority());
        updatedDTO.setCreated_at(updatedTask.getCreated_at());
        updatedDTO.setUpdated_at(updatedTask.getUpdated_at());
        updatedDTO.setFinished_at(updatedTask.getFinished_at());
        updatedDTO.setUserId(updatedTask.getUser() != null ? updatedTask.getUser().getId() : null);

        if (updatedTask.getTags() != null) {
            List<TagDTO> tagDTOs = updatedTask.getTags().stream()
                    .map(tag -> {
                        TagDTO tagDTO = new TagDTO();
                        tagDTO.setId(tag.getId());
                        tagDTO.setName(tag.getName());
                        return tagDTO;
                    })
                    .collect(Collectors.toList());
            updatedDTO.setTags(tagDTOs);
        } else {
            updatedDTO.setTags(new ArrayList<>());
        }

        return ResponseEntity.ok(updatedDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        Task existingTask = taskService.findById(id);
        if (existingTask == null) {
            return ResponseEntity.notFound().build();
        }
        taskService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
