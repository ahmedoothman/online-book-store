package com.othman.onlinebookstore.service;

import java.util.Collections;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.othman.onlinebookstore.DTO.LoginRequestDTO;
import com.othman.onlinebookstore.DTO.LoginResponseDTO;
import com.othman.onlinebookstore.DTO.RegisterRequestDTO;
import com.othman.onlinebookstore.entity.Role;
import com.othman.onlinebookstore.entity.UserEntity;
import com.othman.onlinebookstore.security.JWTGenerator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;
    public UserEntity register(RegisterRequestDTO registerRequestDTO){
        
        if (userService.existsByEmail(registerRequestDTO.getEmail())) {
            // trow exception : email exists
        }

        if (registerRequestDTO.getPassword() != registerRequestDTO.getConfirmPassword()) {
            // throw exception : passwords doesn't match
        }

        UserEntity user = new UserEntity();
        Role role = roleService.findByName("USER"); // {id:1,name:"USER"}
        
        user.setName(registerRequestDTO.getName());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRoles(Collections.singletonList(role)); // [{id:1,name:"USER"}]

        return userService.save(user);
    }

    // public String login(LoginRequestDTO loginRequestDTO){
    //     Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));
    //     SecurityContextHolder.getContext().setAuthentication(authentication);
    //     return "Logged in successfuly";
    // }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setEmail(loginRequestDTO.getEmail());
        loginResponseDTO.setToken(token);
        
        return loginResponseDTO;
    }
}
