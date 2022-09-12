package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.BookStoreDTO;
import com.example.bookstoreapp.email.EmailService;
import com.example.bookstoreapp.entity.BookStoreEntity;
import com.example.bookstoreapp.entity.Cart;
import com.example.bookstoreapp.entity.UserData;
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

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    //add data into bookstore table
    public BookStoreEntity addData(String token,BookStoreDTO bookStoreDTO) {
        BookStoreEntity book = new BookStoreEntity(bookStoreDTO);
        UserData userData = userService.getUserById((token));
        if (userData.getIsadmin()==0){
            emailService.sendEmail(userData.getEmail(),"Book Created", "For user book create succssfully..!");
            return bookStoreRepository.save(book);
        }
        else throw new CustomException("Invalid Entry");
    }
    //show all data in the DB post method using
    public List<BookStoreEntity> listAll() {
        if(bookStoreRepository.findAll().isEmpty()){
            throw new CustomException("DB is Empty..!");
        }else
            return bookStoreRepository.findAll();
    }

    //get data by id book deatils
    public BookStoreEntity getById(int id){
        return bookStoreRepository.findById(id).orElseThrow(()->new CustomException("book id" + id + "not found"));
    }

    //find by book name
    public List<BookStoreEntity> findByBookName (String bname){
        List<BookStoreEntity> bookname = bookStoreRepository.findByBookName(bname);
        if (bookname.size()!=0){
            return bookname;
        }else throw new CustomException("Book " + bname + " not available");
    }

    //sort ascending by feild
    public List<BookStoreEntity> sortByAsc (String feild){
        List<BookStoreEntity> bookprice = bookStoreRepository.sortByBookName(feild);
        if ((bookprice.size()!=0)){
            return bookprice;
        }else throw new CustomException("Book Sorted list " + feild + " in ascending order");
    }

    //sort descening order by feild
    public List<BookStoreEntity> sortByDesc (String feild){
        List<BookStoreEntity> bookprice = bookStoreRepository.sortByBookPrice(feild);
        if ((bookprice.size()!=0)){
            return bookprice;
        }else throw new CustomException("Book Sorted list " + feild + " in ascending order");
    }

    //delete by id from table
    public String deleteById(int id) {
        if (bookStoreRepository.findById(id).isPresent()) {
            bookStoreRepository.deleteById(id);
            return "Book ID: " + id + " is Deleted Successfully!!";
        } else throw new CustomException("Book ID not found..!");
    }

    //update by id
    public BookStoreEntity editById(BookStoreEntity bookStoreEntity, int id) {
        if (bookStoreRepository.findById(id).isPresent()) {
            BookStoreEntity newData = new BookStoreEntity(id, bookStoreEntity);
            return bookStoreRepository.save(newData);
        }else
            throw new CustomException("Book id not found..!");
    }

}