package com.example.taskFlow.service;

import com.example.taskFlow.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findbyId(Long id);

    boolean existsByEmail(String email);

    User findByEmail(String email);

    User save(User user);

    User update(User user);

    void delete(Long id);

    User getCurrentUser();
}
