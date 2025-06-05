package com.example.taskFlow.service.imp;

import org.springframework.stereotype.Service;
import com.example.taskFlow.service.UserService;
import com.example.taskFlow.repository.UserRepository;
import com.example.taskFlow.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public User findbyId(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
