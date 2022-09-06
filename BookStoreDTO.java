package com.example.bookstoreapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookStoreDTO {

    @NotEmpty
    private  String name;

    @NotEmpty
    private String author;

    private float price;

    private LocalDate arrivaldate;

    private String coverimage;

    private  int quantity;
}
