package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.OrderDTO;
import com.example.bookstoreapp.email.EmailService;
import com.example.bookstoreapp.entity.*;
import com.example.bookstoreapp.exception.CustomException;
import com.example.bookstoreapp.repository.BookStoreRepository;
import com.example.bookstoreapp.repository.CartRepository;
import com.example.bookstoreapp.repository.OrderRepository;
import com.example.bookstoreapp.repository.UserRepository;
import com.example.bookstoreapp.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    TokenUtility tokenUtility;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookStoreRepository bookStoreRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    EmailService emailService;

    // placed user order data - post method
    public OrderData addOrder(OrderDTO orderDTO,String token) {
        int userid = tokenUtility.decodeToken(token);
        Optional<BookStoreEntity> book = bookStoreRepository.findById(orderDTO.getBookid());
        Optional<UserData> user = userRepository.findById(orderDTO.getUserid());
        if (book.isPresent() && user.isPresent()) {
            if (orderDTO.getQuantity() < book.get().getQuantity()) {
                OrderData newOrder = new OrderData(book.get().getPrice(), orderDTO.getQuantity(), orderDTO.getAddress(), book.get(), user.get(), orderDTO.isCancel());
                orderRepository.save(newOrder);
                book.get().setQuantity(book.get().getQuantity() - orderDTO.getQuantity());
                newOrder.setPrice(newOrder.getPrice() * orderDTO.getQuantity());
                bookStoreRepository.save(book.get());
                System.out.println("Order placed  successfully");
                emailService.sendEmail(orderDTO.getEmail(), "Order Placed", "Hello, Your order for " + newOrder + "  is placed successfully on " + newOrder.getDate() + " Thank you..!");
                return newOrder;
            } else {
                throw new CustomException("Requested quantity is not valid");
            }
        } else {
            throw new CustomException("Book id or User id not present");
        }
    }
}