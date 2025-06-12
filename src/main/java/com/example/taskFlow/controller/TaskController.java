package com.example.taskFlow.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.taskFlow.service.TaskService;
import com.example.taskFlow.entity.Task;
import com.example.taskFlow.dto.TaskDTO;
import com.example.taskFlow.dto.TagDTO;
import com.example.taskFlow.service.UserService;
import com.example.taskFlow.service.TagService;
import com.example.taskFlow.entity.Tag;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import com.example.taskFlow.entity.User;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController extends BaseController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        User currentUser = userService.getCurrentUser();
        List<TaskDTO> tasks = taskService.findByUserId(currentUser.getId()).stream()
                .map(task -> convertToDTO(task))
                .collect(Collectors.toList());
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        User currentUser = userService.getCurrentUser();
        Task task = taskService.findById(id);

        if (task == null || !task.getUser().getId().equals(currentUser.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(convertToDTO(task));
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        User currentUser = userService.getCurrentUser();

        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        task.setPriority(taskDTO.getPriority());
        task.setCreated_at(taskDTO.getCreated_at());
        task.setUpdated_at(taskDTO.getUpdated_at());
        task.setFinished_at(taskDTO.getFinished_at());
        task.setUser(currentUser);
        task.setDueDate(taskDTO.getDue_date());

        if (task.getTags() == null) {
            task.setTags(new ArrayList<>());
        }

        if (taskDTO.getTags() != null && !taskDTO.getTags().isEmpty()) {
            List<Tag> userTags = tagService.findByUserId(currentUser.getId());
            List<Tag> validTags = taskDTO.getTags().stream()
                    .map(tagDTO -> tagService.findById(tagDTO.getId()))
                    .filter(tag -> tag != null && userTags.contains(tag))
                    .collect(Collectors.toList());
            task.setTags(validTags);
        }

        Task savedTask = taskService.save(task);
        return ResponseEntity.ok(convertToDTO(savedTask));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        User currentUser = userService.getCurrentUser();
        Task existingTask = taskService.findById(id);

        if (existingTask == null || !existingTask.getUser().getId().equals(currentUser.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        existingTask.setTitle(taskDTO.getTitle());
        existingTask.setDescription(taskDTO.getDescription());
        existingTask.setStatus(taskDTO.getStatus());
        existingTask.setPriority(taskDTO.getPriority());
        existingTask.setCreated_at(taskDTO.getCreated_at());
        existingTask.setUpdated_at(taskDTO.getUpdated_at());
        existingTask.setFinished_at(taskDTO.getFinished_at());
        existingTask.setDueDate(taskDTO.getDue_date());

        if (taskDTO.getTags() != null) {
            List<Tag> userTags = tagService.findByUserId(currentUser.getId());
            List<Tag> validTags = taskDTO.getTags().stream()
                    .map(tagDTO -> tagService.findById(tagDTO.getId()))
                    .filter(tag -> tag != null && userTags.contains(tag))
                    .collect(Collectors.toList());
            existingTask.setTags(validTags);
        } else {
            existingTask.setTags(new ArrayList<>());
        }

        Task updatedTask = taskService.update(existingTask);
        return ResponseEntity.ok(convertToDTO(updatedTask));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        User currentUser = userService.getCurrentUser();
        Task existingTask = taskService.findById(id);

        if (existingTask == null || !existingTask.getUser().getId().equals(currentUser.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        taskService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private TaskDTO convertToDTO(Task task) {
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
        dto.setDue_date(task.getDueDate());

        if (task.getTags() != null) {
            List<TagDTO> tagDTOs = task.getTags().stream()
                    .map(tag -> {
                        TagDTO tagDTO = new TagDTO();
                        tagDTO.setId(tag.getId());
                        tagDTO.setName(tag.getName());
                        tagDTO.setColor(tag.getColor()); 
                        return tagDTO;
                    })
                    .collect(Collectors.toList());
            dto.setTags(tagDTOs);
        } else {
            dto.setTags(new ArrayList<>());
        }

        return dto;
    }
}
