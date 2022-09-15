package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.CartDTO;
import com.example.bookstoreapp.dto.OrderDTO;
import com.example.bookstoreapp.dto.ResponseDTO;
import com.example.bookstoreapp.email.EmailService;
import com.example.bookstoreapp.entity.Cart;
import com.example.bookstoreapp.entity.OrderData;
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

    //list of all in the DB - post method
    @PostMapping("/listall")
    public ResponseEntity<ResponseDTO> listofAll (){
        ResponseDTO responseDTO = new ResponseDTO("List of All Data in the Database", orderService.listAll());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //get data by id from table - get method
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getById (@PathVariable int id){
        ResponseDTO responseDTO = new ResponseDTO("Get order data by ID:", orderService.getByCartId(id));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //delete by id - delete method
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteCart(@Valid @PathVariable int id) {
        ResponseDTO responseDTO = new ResponseDTO("Cart Deleted Successfully", orderService.deleteOrder(id));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //update by id - put method using
    @PutMapping("update/{id}")
    public ResponseEntity<ResponseDTO> editUser(@Valid @RequestBody OrderDTO orderDTO, @PathVariable int id,@RequestParam String token) {
        ResponseDTO responseDTO = new ResponseDTO("Cart Updated Successfully", orderService.edit(orderDTO,id,token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
