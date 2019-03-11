package com.example.druid.controller;

import com.example.druid.entity.User;
import com.example.druid.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/users")
    public User createUser() {
        User user1 = new User();
        user1.setCity("city");
        user1.setJob("job");
        user1.setAddr("addr");
        user1.setCar("car");
        user1.setSex(true);
        user1.setTel("111111111");
        User user = userRepository.save(user1);
        return user;
    }

    @GetMapping("/users/id/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        Optional<User> byId = userRepository.findById(id);
        User user = byId.orElse(null);
        return user;
    }

    @GetMapping("/users/tel/{tel}")
    public User getUserByTel(@PathVariable("tel") String tel) {
        User user = userRepository.findUserByTel(tel);
        return user;
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable("id") Integer id) {
        User one = userRepository.getOne(id);
        one.setAddr("update");
        one.setJob("updateJob");
        one.setCity("updateCity");
        User user = userRepository.save(one);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") Integer id) {
        User one = userRepository.getOne(id);
        userRepository.delete(one);
    }
}
