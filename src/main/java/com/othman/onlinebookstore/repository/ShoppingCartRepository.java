package com.othman.onlinebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.othman.onlinebookstore.entity.ShoppingCart;


public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Integer> {
    
}

