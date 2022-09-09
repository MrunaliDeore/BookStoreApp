package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.BookStoreDTO;
import com.example.bookstoreapp.dto.ResponseDTO;
import com.example.bookstoreapp.entity.BookStoreEntity;
import com.example.bookstoreapp.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/bookstore")
public class BookStoreController {
    @Autowired
    BookStoreService bookStoreService;

    //add data into bookstore table
    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> createUser(@Valid @RequestBody BookStoreDTO bookStoreDTO,@RequestParam String token) {
        ResponseDTO responseDTO = new ResponseDTO("New Book Store into DB Successfully", bookStoreService.addData(token,bookStoreDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    //list of all in the DB - post method
    @PostMapping("/post")
    public ResponseEntity<ResponseDTO> listofAll (){
        ResponseDTO responseDTO = new ResponseDTO("List of All Data in the Database", bookStoreService.listAll());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //get data by id from table - get method
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getById (@PathVariable int bookid){
        ResponseDTO responseDTO = new ResponseDTO("Get data by ID:", bookStoreService.getById(bookid));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //delete by id - delete method
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteBook(@PathVariable int bookid) {
        ResponseDTO responseDTO = new ResponseDTO("Book Deleted Successfully", bookStoreService.deleteById(bookid));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //update by id - put method using
    @PutMapping("update/{id}")
    public ResponseEntity<ResponseDTO> editBook(@Valid @RequestBody BookStoreEntity addressBook, @PathVariable int bookid) {
        ResponseDTO responseDTO = new ResponseDTO("Book Updated Successfully", bookStoreService.editById(addressBook,bookid));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
