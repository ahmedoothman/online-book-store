package com.othman.onlinebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.othman.onlinebookstore.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    
}
