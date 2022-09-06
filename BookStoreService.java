package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.BookStoreDTO;
import com.example.bookstoreapp.entity.BookStoreEntity;
import com.example.bookstoreapp.exception.CustomException;
import com.example.bookstoreapp.repository.BookStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookStoreService {

    @Autowired
    BookStoreRepository bookStoreRepository;

    //add data into bookstore table
    public BookStoreEntity addData(BookStoreEntity bookStoreEntity) {
        bookStoreRepository.save(bookStoreEntity);
        return bookStoreEntity;
    }

    //show all data in the DB post method using
    public List<BookStoreEntity> listAll() {
        if(bookStoreRepository.findAll().isEmpty()){
            throw new CustomException("DB is Empty..!");
        }else
            return bookStoreRepository.findAll();
    }

    //get data by id book deatils
    public Optional<BookStoreEntity> getById(int bookid) {
        if(bookStoreRepository.findById(bookid).isPresent()){
            return bookStoreRepository.findById(bookid);
        }else
            throw new CustomException("Book ID not found..!");
    }

    //delete by id from table
    public String deleteById(int bookid) {
        if (bookStoreRepository.findById(bookid).isPresent()) {
            bookStoreRepository.deleteById(bookid);
            return "Book ID: " + bookid + " is Deleted Successfully!!";
        } else throw new CustomException("Book ID not found..!");
    }

    //update by id
    public BookStoreEntity editById(BookStoreEntity bookStoreEntity, int bookid) {
        if (bookStoreRepository.findById(bookid).isPresent()) {
            BookStoreEntity newData = new BookStoreEntity(bookid, bookStoreEntity);
            return bookStoreRepository.save(newData);
        }else
            throw new CustomException("Book id not found..!");
    }
}
