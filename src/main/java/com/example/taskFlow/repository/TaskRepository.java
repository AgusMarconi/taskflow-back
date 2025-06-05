package com.example.taskFlow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.taskFlow.entity.Task;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTagsId(Long tagId);
    List<Task> findByUserId(Long userId);
}
