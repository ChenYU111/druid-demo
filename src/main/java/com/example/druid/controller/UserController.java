package com.example.druid.controller;

import com.example.druid.entity.User;
import com.example.druid.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") Integer id){
        Optional<User> byId = userRepository.findById(id);
        User user = byId.orElse(null);
        return user;
    }
}
