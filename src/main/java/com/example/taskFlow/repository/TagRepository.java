package com.example.taskFlow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.taskFlow.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
