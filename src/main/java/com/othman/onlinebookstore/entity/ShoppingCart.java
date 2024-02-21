package com.othman.onlinebookstore.entity;

import java.util.List;


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

    @ManyToMany(mappedBy = "shoppingCarts")
    private List<Book> books;

    
    @OneToOne
    private User user;

    private Double totalPrice;

}
