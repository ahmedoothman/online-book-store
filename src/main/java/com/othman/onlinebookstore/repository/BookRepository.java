package com.othman.onlinebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.othman.onlinebookstore.entity.Book;

public interface BookRepository extends JpaRepository<Book,Integer> {
    
}
