package com.example.taskFlow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taskFlow.entity.Task;
import com.example.taskFlow.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository repository;
	
	public List<Task> findAll() {
		return repository.findAll();
	}
	
	public Task findById(long id) {
		return repository.findById(id).orElse(null);
	}
	
	public Task save(Task task) {
		return repository.save(task);
	}
	
	public Task update(Task task) {
		return repository.save(task);
	}
	
	public void deleteById(long id) {
		repository.deleteById(id);
	}
	
}
