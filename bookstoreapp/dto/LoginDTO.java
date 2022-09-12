package com.example.bookstoreapp.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoginDTO {
    
    public String loginid;
    public String password;
}
