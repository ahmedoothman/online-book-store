package com.othman.onlinebookstore.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @ManyToMany
    @JsonIgnoreProperties("transactions")
    private List<Book> books;

    @ManyToOne
    @JsonManagedReference
    private UserEntity user;

    private Double totalPrice;

    @Temporal(TemporalType.TIMESTAMP)
    private Date issuedData;

    
}
