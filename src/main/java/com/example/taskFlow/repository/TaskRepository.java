package com.example.taskFlow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.taskFlow.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
