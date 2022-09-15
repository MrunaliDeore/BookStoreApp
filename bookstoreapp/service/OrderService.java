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
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        Optional<UserData> user = userRepository.findById(userid);
        if (book.isPresent() && user.isPresent()) {
            if (orderDTO.getQuantity() < book.get().getQuantity()) {
                OrderData newOrder = new OrderData(book.get().getPrice(), orderDTO.getQuantity(),user.get().getAddress(), book.get(), user.get(), orderDTO.isCancel());
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

    //show all data list in the DB post method using
    public List<OrderData> listAll() {
        if(orderRepository.findAll().isEmpty()){
            throw new CustomException("DB is Empty..!");
        }else
            return orderRepository.findAll();
    }

    public Optional<OrderData> getByCartId(int id) {
        if(orderRepository.findById(id).isPresent()){
            return orderRepository.findById(id);
        }else
            throw new CustomException("Order Id not found..!");
    }

    //delete rder by using id
    public String deleteOrder(int id){
        if (orderRepository.findById(id).isPresent()){
            orderRepository.deleteById(id);
            return "Order ID: " + id +  "Deleted Successfully..!";
        }else
            throw new CustomException("Id is not match do delete the order");
    }

    //update by id

    public OrderData edit (OrderDTO orderDTO,int id,String token) {
        int userid = tokenUtility.decodeToken(token);
        Optional<BookStoreEntity> book = bookStoreRepository.findById(orderDTO.getBookid());
        Optional<UserData> user = userRepository.findById(userid);
        if (orderRepository.findById(id).isPresent()) {
            if (book.isPresent() && user.isPresent()) {
                if (orderDTO.getQuantity() < book.get().getQuantity()) {
                    OrderData editData = new OrderData(id, book.get().getPrice(), orderDTO.getQuantity(), user.get().getAddress(), book.get(), user.get(), orderDTO.isCancel());
                    orderRepository.save(editData);
                    book.get().setQuantity(book.get().getQuantity() - orderDTO.getQuantity());
                    editData.setPrice(editData.getPrice() * orderDTO.getQuantity());
                    bookStoreRepository.save(book.get());
                    System.out.println("Placed  order edited successfully");
                    emailService.sendEmail(orderDTO.getEmail(), "Order Placed Edited", "Hello, Your order for " + editData + "  is edited successfully on " + editData.getDate() + " Thank you..!");
                    return editData;
                } else {
                    throw new CustomException("Requested quantity is not valid");
                }
            } else {
                throw new CustomException("Book id or User id not present");
            }
        }else
            throw new CustomException("Order id not found..!");
    }
}