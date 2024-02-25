package com.othman.onlinebookstore.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.othman.onlinebookstore.entity.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="users")
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false , unique = true)
    private String email; // candidate key
    @Column(nullable = false)
    private String password;
    
    @Enumerated(EnumType.STRING)
    private UserRole role;
    
    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<TransactionHistory> transaction;
}
