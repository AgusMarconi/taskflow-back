package com.example.taskFlow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.taskFlow.entity.Tag;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query("SELECT DISTINCT t FROM Tag t JOIN t.tasks task WHERE task.user.id = :userId")
    List<Tag> findByUserId(@Param("userId") Long userId);
}
