package com.example.taskFlow.service.imp;

import org.springframework.stereotype.Service;
import com.example.taskFlow.service.TagService;
import com.example.taskFlow.repository.TagRepository;
import com.example.taskFlow.entity.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class TagServiceImp implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag findById(Long id) {
        return tagRepository.findById(id).orElse(null);
    }

    @Override
    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag update(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public void deleteById(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public List<Tag> findByUserId(Long userId) {
        return tagRepository.findByUserId(userId);
    }
}
