package com.othman.onlinebookstore.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class ShoppingCart {
    
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToMany
    @JsonIgnoreProperties("shoppingCart")
    private List<Book> books = new ArrayList<>();

    @OneToOne
    private UserEntity user;

    @Column(nullable = false)
    private Double totalPrice;

}
