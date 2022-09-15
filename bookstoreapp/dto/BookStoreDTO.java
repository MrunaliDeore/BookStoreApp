package com.example.bookstoreapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookStoreDTO {
    @Pattern(regexp = "[A-za-z]{3,}", message = "Invalid book name, Book name should be 4 letters")
    private  String name;

    @Pattern(regexp = "[A-za-z]{3,}", message = "Invalid book name, Book name should be 4 letters")
    private String author;

    @Min(value=20, message = "Minimum price should be 20")
    private float price;

    private LocalDate arrivaldate;

    private String coverimage;

    private  int quantity;
}
