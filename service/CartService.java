package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.BookStoreDTO;
import com.example.bookstoreapp.dto.CartDTO;
import com.example.bookstoreapp.entity.BookStoreEntity;
import com.example.bookstoreapp.entity.Cart;
import com.example.bookstoreapp.entity.UserData;
import com.example.bookstoreapp.exception.CustomException;
import com.example.bookstoreapp.repository.BookStoreRepository;
import com.example.bookstoreapp.repository.CartRepository;
import com.example.bookstoreapp.repository.UserRepository;
import com.example.bookstoreapp.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    BookStoreService bookStoreService;
    @Autowired
    UserService userService;

    @Autowired
    CartDTO cartDTO;

    @Autowired
    TokenUtility tokenUtility;

    @Autowired
    BookStoreRepository bookStoreRepository;

    public Cart addCart(CartDTO cartDTO, String token) {
        int userid = tokenUtility.decodeToken(token);
        Optional<UserData> userData = userRepository.findById(userid);
        Optional<BookStoreEntity> book = bookStoreRepository.findById(cartDTO.getBookid());
        if (userData.isPresent() && book.isPresent()) {
            Cart cart = new Cart(userData.get(), book.get(), cartDTO.getQuantity(), cartDTO.getTotalprice());
            cartRepository.save(cart);
            return cart;
        } else {
            throw new CustomException("Userid & bookid not present");
        }
    }

}