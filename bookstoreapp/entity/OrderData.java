package com.example.bookstoreapp.entity;

import com.example.bookstoreapp.dto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orderdata")
public class OrderData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderid;
    private float price;
    private int quantity;
    private String address;
    private boolean cancel;

    private LocalDate date = LocalDate.now();

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserData userData;

    @OneToOne
    @JoinColumn(name = "book_id")
    private BookStoreEntity bookStoreEntity;


    public OrderData(float price, int quantity, String address, BookStoreEntity bookStoreEntity, UserData userData, boolean cancel) {
        this.price=price;
        this.quantity=quantity;
        this.address=address;
        this.bookStoreEntity = bookStoreEntity;
        this.userData=userData;
        this.cancel = cancel;
    }

    public OrderData(int id, float price, int quantity, String address, BookStoreEntity bookStoreEntity, UserData userData, boolean cancel) {
        this.orderid=id;
        this.price=price;
        this.quantity=quantity;
        this.address=address;
        this.bookStoreEntity = bookStoreEntity;
        this.userData=userData;
        this.cancel = cancel;
    }
}
