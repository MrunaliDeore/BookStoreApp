package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.BookStoreDTO;
import com.example.bookstoreapp.dto.CartDTO;
import com.example.bookstoreapp.dto.ResponseDTO;
import com.example.bookstoreapp.entity.Cart;
import com.example.bookstoreapp.entity.UserData;
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

    //delete by id - delete method
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteCart(@Valid @PathVariable int id) {
        ResponseDTO responseDTO = new ResponseDTO("Cart Deleted Successfully", cartService.deleteCart(id));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //get data by id from table - get method
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getById (@PathVariable int id){
        ResponseDTO responseDTO = new ResponseDTO("Get cart data by ID:", cartService.getByCartId(id));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //list of all in the DB - post method
    @PostMapping("/listall")
    public ResponseEntity<ResponseDTO> listofAll (){
        ResponseDTO responseDTO = new ResponseDTO("List of All Data in the Database", cartService.listAll());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //update by id - put method using
    @PutMapping("update/{id}")
    public ResponseEntity<ResponseDTO> editUser(@Valid @RequestBody Cart cart, @PathVariable int id) {
        ResponseDTO responseDTO = new ResponseDTO("Cart Updated Successfully", cartService.editById(cart,id));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
