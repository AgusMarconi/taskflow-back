package com.example.taskFlow.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.taskFlow.service.TagService;
import com.example.taskFlow.service.TaskService;
import com.example.taskFlow.service.UserService;
import com.example.taskFlow.entity.Tag;
import com.example.taskFlow.entity.Task;
import com.example.taskFlow.entity.User;
import com.example.taskFlow.dto.TagDTO;
import org.springframework.http.HttpStatus;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @GetMapping(value = { "", "/"})
    public ResponseEntity<List<Tag>> getAllTagsForCurrentUser() {
        User currentUser = userService.getCurrentUser();
        List<Tag> tags = tagService.findByUserId(currentUser.getId());
        return ResponseEntity.ok(tags);
    }

    @PostMapping
    public ResponseEntity<Tag> createTag(@RequestBody TagDTO tagDTO) {
        Tag tag = new Tag();
        tag.setName(tagDTO.getName());

        if (tagDTO.getTask_id() != null) {
            Task task = taskService.findById(tagDTO.getTask_id());
            if (task == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            User authenticatedUser = userService.getCurrentUser();
            if (!task.getUser().getId().equals(authenticatedUser.getId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            if (task.getTags() == null) {
                task.setTags(new ArrayList<>());
            }
            if (tag.getTasks() == null) {
                tag.setTasks(new ArrayList<>());
            }

            tag = tagService.save(tag);

            task.getTags().add(tag);
            tag.getTasks().add(task);

            taskService.update(task);
            tag = tagService.update(tag);
        } else {
            tag = tagService.save(tag);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(tag);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable Long id) {
        Tag tag = tagService.findById(id);
        if (tag == null) {
            return ResponseEntity.notFound().build();
        }

        if (tag.getTasks() != null && !tag.getTasks().isEmpty()) {
            User authenticatedUser = userService.getCurrentUser();
            boolean hasAccess = tag.getTasks().stream()
                    .anyMatch(task -> task.getUser().getId().equals(authenticatedUser.getId()));
            if (!hasAccess) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }

        return ResponseEntity.ok(tag);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable Long id, @RequestBody TagDTO tagDTO) {
        Tag existingTag = tagService.findById(id);
        if (existingTag == null) {
            return ResponseEntity.notFound().build();
        }

        if (existingTag.getTasks() != null && !existingTag.getTasks().isEmpty()) {
            User authenticatedUser = userService.getCurrentUser();
            boolean ownsAllTasks = existingTag.getTasks().stream()
                    .allMatch(task -> task.getUser().getId().equals(authenticatedUser.getId()));
            if (!ownsAllTasks) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }

        existingTag.setName(tagDTO.getName());
        return ResponseEntity.ok(tagService.update(existingTag));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        Tag tag = tagService.findById(id);
        if (tag == null) {
            return ResponseEntity.notFound().build();
        }

        if (tag.getTasks() != null && !tag.getTasks().isEmpty()) {
            User authenticatedUser = userService.getCurrentUser();
            boolean ownsAllTasks = tag.getTasks().stream()
                    .allMatch(task -> task.getUser().getId().equals(authenticatedUser.getId()));
            if (!ownsAllTasks) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }

        tagService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
