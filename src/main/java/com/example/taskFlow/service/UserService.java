package com.example.taskFlow.service;
import com.example.taskFlow.entity.User;

public interface UserService {
    User findbyId(Long id);
    User findByEmail(String email);
    User save(User user);
    User update(User user);
    void delete(Long id);
}
