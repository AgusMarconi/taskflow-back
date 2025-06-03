package com.example.taskFlow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskFlow.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

}
