package com.example.taskFlow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taskFlow.entity.Tag;
import com.example.taskFlow.repository.TagRepository;

@Service
public class TagService {

	@Autowired
	private TagRepository repository;
	
	public List<Tag> findAll() {
		return repository.findAll();
	}
	
	public Tag findById(long id) {
		return repository.findById(id).orElse(null);
	}
	
	public Tag save(Tag tag) {
		return repository.save(tag);
	}
	
	public Tag update(Tag tag) {
		return repository.save(tag);
	}
	
	public void deleteById(long id) {
		repository.deleteById(id);
	}
	
}
