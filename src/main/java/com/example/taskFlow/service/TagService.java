package com.example.taskFlow.service;

import com.example.taskFlow.entity.Tag;

public interface TagService {
    Tag findById(Long id);
    Tag save(Tag tag);
    Tag update(Tag tag);
    void deleteById(Long id);
}
