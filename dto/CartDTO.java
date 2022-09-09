package com.example.bookstoreapp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class CartDTO {

    public int userid;
    public int bookid;
    public int quantity;
    public long totalprice;

}
