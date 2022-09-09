package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.BookStoreDTO;
import com.example.bookstoreapp.dto.ResponseDTO;
import com.example.bookstoreapp.dto.UserDTO;
import com.example.bookstoreapp.entity.BookStoreEntity;
import com.example.bookstoreapp.entity.UserData;
import com.example.bookstoreapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/userdata")
public class UserController {

    @Autowired
    UserService userService;

    //add data into user table
    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        ResponseDTO responseDTO = new ResponseDTO("New user add into DB Successfully", userService.addData(userDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    //list of all in the DB - post method
    @PostMapping("/post")
    public ResponseEntity<ResponseDTO> listofAll (){
        ResponseDTO responseDTO = new ResponseDTO("List of All Data in the Database", userService.listAll());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //get data by id from table - get method
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getById (@PathVariable int userid){
        ResponseDTO responseDTO = new ResponseDTO("Get data by ID:", userService.getUserById(String.valueOf(userid)));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //delete by id - delete method
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable int userid) {
        ResponseDTO responseDTO = new ResponseDTO("Book Deleted Successfully", userService.deleteById(userid));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //update by id - put method using
    @PutMapping("update/{id}")
    public ResponseEntity<ResponseDTO> editUser(@Valid @RequestBody UserData userData, @PathVariable int userid) {
        ResponseDTO responseDTO = new ResponseDTO("Book Updated Successfully", userService.editById(userData,userid));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
