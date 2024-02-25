package com.othman.onlinebookstore.service;


import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.othman.onlinebookstore.DTO.BookCartDTO;
import com.othman.onlinebookstore.DTO.TransactionDTO;
import com.othman.onlinebookstore.entity.Book;
import com.othman.onlinebookstore.entity.TransactionHistory;
import com.othman.onlinebookstore.entity.UserEntity;
import com.othman.onlinebookstore.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {
    
    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final BookService bookService;

    // get all transcations
    public List<TransactionHistory> getAllTransactions(){
        return transactionRepository.findAll();
    }
    // get transaction by id
    public TransactionHistory getTransactionById(Integer id){
        return transactionRepository.findById(id).orElse(null);
    }
    // delete transction by id
    public void deleteTransactionById(Integer id){
        transactionRepository.deleteById(id);
    }

    // create transaction
    public  TransactionHistory createTransaction(TransactionDTO transactionDTO){
        UserEntity user = userService.getUserById(transactionDTO.getUserId());
        TransactionHistory transaction = new TransactionHistory();     //  [bookObj,bookObj]
        List<BookCartDTO> requiredBooks = transactionDTO.getBooks(); // [{bookId:1,quantity:1},{bookId:2,quantity:1}]
        List<Integer> bookIds = requiredBooks.stream().map(bookObj -> bookObj.getBookId()).toList(); // [1,2]
        List<Book> books = bookService.getAllBooksByIds(bookIds); // books [{id:1,name:c++,price:20,quantity:10},{id:1,name:c++,price:20,quantity:10}]
        // update quantity in db
        for(Book book : books){
          for(BookCartDTO bookObj : requiredBooks){
            if (book.getId() == bookObj.getBookId()) {
                book.setQuantity(book.getQuantity() - bookObj.getQuantity());
            }
          }
        }
        // bookService.saveBooks(books);
        // calculate total price
        Double totalPrice = 0.0; // 5000
        for(Book book : books){
            for(BookCartDTO bookObj : requiredBooks){
              if (book.getId() == bookObj.getBookId()) {
                  totalPrice += book.getPrice() * bookObj.getQuantity();
              }
            }
          }
        // set transaction
          transaction.setBooks(books);
          transaction.setTotalPrice(totalPrice);
          transaction.setUser(user);
          transaction.setIssuedData(new Date());
        // save Transcation
        return transactionRepository.save(transaction);
    }

}
