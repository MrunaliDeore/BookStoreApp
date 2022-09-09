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

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserData userData;

    @ManyToOne
    @JoinColumn(name = "book_id")
    public BookStoreEntity bookStoreEntity;

    public long totalprice;
    public int quantity;

    public Cart(UserData userData, BookStoreEntity book, int quantity,long totalprice) {
        this.userData = userData;
        this.bookStoreEntity = book;
        this.quantity = quantity;
        this.totalprice = totalprice;
    }




}
