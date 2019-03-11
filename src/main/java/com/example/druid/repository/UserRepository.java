package com.example.druid.repository;

import com.example.druid.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("select u from User u where tel=?1")
    User findUserByTel(String tel);
}
