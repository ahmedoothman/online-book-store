package com.othman.onlinebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.othman.onlinebookstore.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    
}
