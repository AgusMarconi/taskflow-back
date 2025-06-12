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
import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private UserService userService;

    @GetMapping(value = { "", "/" })
    public ResponseEntity<List<TagDTO>> getAllTagsForCurrentUser() {
        User currentUser = userService.getCurrentUser();
        List<TagDTO> tagDTOs = tagService.findByUserId(currentUser.getId()).stream()
                .map(tag -> new TagDTO(
                        tag.getId(),
                        tag.getName(),
                        tag.getColor(),
                        tag.getTasks() == null
                                ? List.of()
                                : tag.getTasks().stream().map(Task::getId).toList()))
                .toList();
        return ResponseEntity.ok(tagDTOs);
    }

    @PostMapping
    public ResponseEntity<TagDTO> createTag(@RequestBody TagDTO tagDTO) {
        User currentUser = userService.getCurrentUser();
        Tag tag = new Tag();
        tag.setName(tagDTO.getName());
        tag.setColor(tagDTO.getColor());
        tag.setUser(currentUser);

        tag = tagService.save(tag);

        TagDTO responseDTO = new TagDTO(tag.getId(), tag.getName(), tag.getColor(), null);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDTO> getTagById(@PathVariable Long id) {
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

        TagDTO tagDTO = new TagDTO(tag.getId(), tag.getName(), tag.getColor(), null);
        return ResponseEntity.ok(tagDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagDTO> updateTag(@PathVariable Long id, @RequestBody TagDTO tagDTO) {
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
        existingTag.setColor(tagDTO.getColor());
        Tag updatedTag = tagService.update(existingTag);
        TagDTO responseDTO = new TagDTO(updatedTag.getId(), updatedTag.getName(), updatedTag.getColor(), null);
        return ResponseEntity.ok(responseDTO);
    }
}
