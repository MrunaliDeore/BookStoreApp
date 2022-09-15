package com.example.bookstoreapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{3,}$", message = "User firstnme is invalid, Firstletter should be captital and min. 3 letter of user firstname")
    private String firstname;

    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{3,}$", message = "User lastname is invalid, Firstletter should be captital and min. 3 letter of user lastname")
    private String lastname;

    @Pattern(regexp = "[a-z0-9]+@gmail.com", message = "Email is not valid, for eg for email : mrunali11@gmail.com")
    private String email;

    @NotEmpty(message = "Address should not be empty")
    private  String address;

    @Pattern(regexp = "^[a-z]{3,}",message = "login id is invalid, should be small letters and min. 3 characters")
    private String loginid;

    @Pattern(regexp = "(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%]).{5,12}", message = "Password is invalid, password should be min 5 and max 12 letter with1 uppercase 1 lowercase 1 special char and 1 number")
    private String password;

    private byte isadmin;


}