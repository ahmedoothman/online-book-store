package com.othman.onlinebookstore.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
public class TransactionHistory {
        
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToMany(mappedBy = "transactions")
    private List<Book> books;

    @ManyToOne
    private User user;

    private Double totalPrice;

    @Temporal(TemporalType.DATE)
    private Date issuedData;

    
}
