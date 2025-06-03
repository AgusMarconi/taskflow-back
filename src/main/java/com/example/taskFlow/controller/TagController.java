package com.example.taskFlow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskFlow.entity.Tag;
import com.example.taskFlow.service.TagService;

@RestController
@RequestMapping("api/v1/tag")
public class TagController {
	
	@Autowired
	private TagService service;
	
	@GetMapping
	public ResponseEntity<List<Tag>> getAll() {
		List<Tag> tags = service.findAll();
		return ResponseEntity.ok(tags);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tag> getById(@PathVariable Long id) {
		Tag tag = service.findById(id);
		return ResponseEntity.ok(tag);
	}
	
	@PostMapping
	public ResponseEntity<Tag> create(@RequestBody Tag tag) {
		Tag createdUser = service.save(tag);
		return ResponseEntity.ok(createdUser);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Tag> update(@RequestBody Tag tag) {
		Tag updatedtag = service.update(tag);
		return ResponseEntity.ok(updatedtag);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
