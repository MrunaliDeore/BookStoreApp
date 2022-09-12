package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.CartDTO;
import com.example.bookstoreapp.dto.OrderDTO;
import com.example.bookstoreapp.dto.ResponseDTO;
import com.example.bookstoreapp.email.EmailService;
import com.example.bookstoreapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    //add data into order table
    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> createOrder(@Valid @RequestBody OrderDTO orderDTO,@RequestParam String token) {
        ResponseDTO responseDTO = new ResponseDTO("New order deatails store into DB Successfully", orderService.addOrder(orderDTO,token));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}
