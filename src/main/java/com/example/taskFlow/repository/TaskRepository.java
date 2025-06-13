package com.example.taskFlow.repository;

import com.example.taskFlow.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByTagsId(Long tagId);

    List<Task> findByUserId(Long userId);

    List<Task> findByDueDateBefore(LocalDateTime date);

    List<Task> findByDueDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
