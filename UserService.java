package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.UserDTO;
import com.example.bookstoreapp.email.EmailService;
import com.example.bookstoreapp.entity.BookStoreEntity;
import com.example.bookstoreapp.entity.UserData;
import com.example.bookstoreapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    //add data into userdata table
    public UserData addData(UserDTO userDTO) {
        UserData userData = new UserData(userDTO);
        emailService.sendEmail(userDTO.getEmail(),"User created", "Hello you have successfully added new user");
        return userRepository.save(userData);
    }
}
