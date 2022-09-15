package com.example.bookstoreapp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Component
public class CartDTO {

    public int userid;

    public int bookid;

    public int quantity;

}
