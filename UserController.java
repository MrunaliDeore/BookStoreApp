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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
