package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.LoginDTO;
import com.example.bookstoreapp.dto.ResponseDTO;
import com.example.bookstoreapp.dto.UserDTO;
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

    //login
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        ResponseDTO responseDTO = new ResponseDTO("Login Successfully Done", userService.loginUser(loginDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //list of all in the DB - post method
    @PostMapping("/listall")
    public ResponseEntity<ResponseDTO> listofAll (){
        ResponseDTO responseDTO = new ResponseDTO("List of All Data in the Database", userService.listAll());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //get data by id from table - get method
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getById (@Valid @PathVariable int id){
        ResponseDTO responseDTO = new ResponseDTO("Get data by ID:", userService.getUserById(String.valueOf(id)));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //delete by id - delete method
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteUser(@Valid @PathVariable int userid) {
        ResponseDTO responseDTO = new ResponseDTO("Book Deleted Successfully", userService.deleteById(userid));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //update by id - put method using
    @PutMapping("update/{id}")
    public ResponseEntity<ResponseDTO> editUser(@Valid @RequestBody UserData userData, @PathVariable int id) {
        ResponseDTO responseDTO = new ResponseDTO("Book Updated Successfully", userService.editById(userData,id));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}