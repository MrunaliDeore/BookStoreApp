package com.example.bookstoreapp.entity;

import com.example.bookstoreapp.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private int userid;

    private String firstname;
    private String lastname;
    private String email;
    private  String address;

    @Column(name = "login_id", nullable = false)
    private String loginid;

    private String password;

    @Column(name = "is_admin", nullable = false)
    private boolean isadmin;

    public UserData(UserDTO userDTO) {
        this.firstname = userDTO.getFirstname();
        this.lastname = userDTO.getLastname();
        this.email = userDTO.getEmail();
        this.address = userDTO.getAddress();
        this.loginid = userDTO.getLoginid();
        this.password = userDTO.getPassword();
        this.isadmin = userDTO.isIsadmin();

    }
}
