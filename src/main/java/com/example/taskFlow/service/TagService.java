package com.example.taskFlow.service;

import com.example.taskFlow.entity.Tag;

import java.util.List;

public interface TagService {
    Tag findById(Long id);

    Tag save(Tag tag);

    Tag update(Tag tag);

    void deleteById(Long id);

    List<Tag> findByUserId(Long userId);
}
