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

import com.example.taskFlow.entity.Task;
import com.example.taskFlow.service.TaskService;

@RestController
@RequestMapping("api/v1/task")
public class TaskController {
	
	@Autowired
	private TaskService service;
	
	@GetMapping
	public ResponseEntity<List<Task>> getAll() {
		List<Task> tasks = service.findAll();
		return ResponseEntity.ok(tasks);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Task> getById(@PathVariable Long id) {
		Task task = service.findById(id);
		return ResponseEntity.ok(task);
	}
	
	@PostMapping
	public ResponseEntity<Task> create(@RequestBody Task task) {
		Task createdTask = service.save(task);
		return ResponseEntity.ok(createdTask);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Task> update(@RequestBody Task task) {
		Task updatedTask = service.update(task);
		return ResponseEntity.ok(updatedTask);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
