package com.example.bookstoreapp.entity;

import com.example.bookstoreapp.dto.CartDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor

public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int cartid;

   // @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserData userData;


    @OneToOne
    @JoinColumn(name = "book_id")
    public BookStoreEntity bookStoreEntity;

    public int quantity;

    public Cart(UserData userData, BookStoreEntity book, int quantity) {
        this.userData = userData;
        this.bookStoreEntity = book;
        this.quantity = quantity;

    }

    public Cart(int cartid, UserData userData, BookStoreEntity bookStoreEntity, int quantity) {
        this.cartid = cartid;
        this.userData = userData;
        this.bookStoreEntity = bookStoreEntity;
        this.quantity = quantity;
    }

    public Cart(int id, Cart cart) {
        this.cartid = id;
        this.cartid = cartid;
        this.userData = userData;
        this.bookStoreEntity = bookStoreEntity;
        this.quantity = quantity;
    }
}
