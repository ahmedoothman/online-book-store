package com.othman.onlinebookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.othman.onlinebookstore.DTO.UserDTO;
import com.othman.onlinebookstore.entity.UserEntity;
import com.othman.onlinebookstore.entity.enums.UserRole;
import com.othman.onlinebookstore.exception.UserNotFoundException;
import com.othman.onlinebookstore.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;

    // Method : Create User
    public UserEntity createUser(UserDTO userDTO){
      
        UserEntity user = new UserEntity();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword()); // hashed password
        user.setRole(UserRole.ADMIN);
        return userRepository.save(user);
    }

    public UserEntity getUserById(Integer userId){
        return userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User Not Found with that id :"+ userId));
    }

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }
}