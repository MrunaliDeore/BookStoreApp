package com.example.bookstoreapp.dto;


import com.example.bookstoreapp.entity.UserData;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@Component
public class OrderDTO {

    public int userid;
    public int bookid;
    public int quantity;
    public float price;
    public String address;
    public String email;
    private boolean cancel;


}
