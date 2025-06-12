package com.example.taskFlow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.taskFlow.entity.Tag;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByUserId(Long userId);
}
