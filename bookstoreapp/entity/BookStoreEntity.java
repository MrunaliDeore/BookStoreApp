package com.example.bookstoreapp.entity;

import com.example.bookstoreapp.dto.BookStoreDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookStoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id", nullable = false)
    private int bookid;
    private  String name;
    private String author;
    private float price;
    private LocalDate arrivaldate;
    private String coverimage;
    private  int quantity;

    public BookStoreEntity(BookStoreDTO bookStoreDTO) {
        this.name = bookStoreDTO.getName();
        this.author = bookStoreDTO.getAuthor();
        this.price = bookStoreDTO.getPrice();
        this.arrivaldate = bookStoreDTO.getArrivaldate();
        this.coverimage = bookStoreDTO.getCoverimage();
        this.quantity = bookStoreDTO.getQuantity();

    }

    public BookStoreEntity(int bookid, BookStoreEntity bookStoreEntity) {
        this.bookid = bookid;
        this.name = bookStoreEntity.getName();
        this.author = bookStoreEntity.getAuthor();
        this.price = bookStoreEntity.getPrice();
        this.arrivaldate = bookStoreEntity.getArrivaldate();
        this.coverimage = bookStoreEntity.getCoverimage();
        this.quantity = bookStoreEntity.getQuantity();
    }


}
