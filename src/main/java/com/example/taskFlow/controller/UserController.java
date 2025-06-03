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

import com.example.taskFlow.entity.User;
import com.example.taskFlow.service.UserService;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<User>> getAll() {
		List<User> users = service.findAll();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable Long id) {
		User user = service.findById(id);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping
	public ResponseEntity<User> create(@RequestBody User user) {
		User createdUser = service.save(user);
		return ResponseEntity.ok(createdUser);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@RequestBody User user) {
		User updatedUser = service.update(user);
		return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
