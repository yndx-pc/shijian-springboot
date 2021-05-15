package com.example.shijian.repository;

import com.example.shijian.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select * from user where name = ?1",nativeQuery = true)
    public List<User> findByName(String name);
}
