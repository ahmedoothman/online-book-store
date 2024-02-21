package com.othman.onlinebookstore.entity;

import java.util.List;

import org.hibernate.annotations.Check;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class Book {
    
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Check(constraints = "price >= 0")
    private Double price;

    @Check(constraints = "quantity >= 0")
    private Integer quantity;

    @ManyToMany
    private List<TransactionHistory> transactions;

    @ManyToMany
    private List<ShoppingCart> shoppingCarts;


    @Transient
    private boolean available;
    public boolean getAvailable(){
        return quantity > 0;
    }
}
