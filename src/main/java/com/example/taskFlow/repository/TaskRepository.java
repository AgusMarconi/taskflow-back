package com.example.taskFlow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskFlow.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
