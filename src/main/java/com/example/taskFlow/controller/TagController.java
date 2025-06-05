package com.example.taskFlow.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.taskFlow.service.TagService;
import com.example.taskFlow.service.TaskService;
import com.example.taskFlow.entity.Tag;
import com.example.taskFlow.entity.Task;
import com.example.taskFlow.dto.TagDTO;
import org.springframework.http.HttpStatus;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/tags")
public class TagController {
    
    @Autowired
    private TagService tagService;

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Tag> createTag(@RequestBody TagDTO tagDTO) {
        Tag tag = new Tag();
        tag.setName(tagDTO.getName());

        if (tagDTO.getTaskId() != null) {
            Task task = taskService.findById(tagDTO.getTaskId());
            if (task == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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
        return ResponseEntity.ok(tag);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable Long id, @RequestBody TagDTO tagDTO) {
        Tag existingTag = tagService.findById(id);
        if (existingTag == null) {
            return ResponseEntity.notFound().build();
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
        tagService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
