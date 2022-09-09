package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.BookStoreDTO;
import com.example.bookstoreapp.dto.CartDTO;
import com.example.bookstoreapp.dto.ResponseDTO;
import com.example.bookstoreapp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    //add data into cart table
    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> createCart(@Valid @RequestBody CartDTO cartDTO, @RequestParam String token) {
        ResponseDTO responseDTO = new ResponseDTO("New cart deatails store into DB Successfully", cartService.addCart(cartDTO,token));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}
