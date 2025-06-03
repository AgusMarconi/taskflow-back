package com.example.taskFlow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taskFlow.entity.User;
import com.example.taskFlow.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(long id) {
		return repository.findById(id).orElse(null);
	}
	
	public User save(User user) {
		return repository.save(user);
	}
	
	public User update(User user) {
		return repository.save(user);
	}
	
	public void deleteById(long id) {
		repository.deleteById(id);
	}
	
}
