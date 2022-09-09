package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.UserDTO;
import com.example.bookstoreapp.email.EmailService;
import com.example.bookstoreapp.entity.UserData;
import com.example.bookstoreapp.exception.CustomException;
import com.example.bookstoreapp.repository.UserRepository;
import com.example.bookstoreapp.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    TokenUtility tokenUtility;

    //add data into userdata table
    public String addData(UserDTO userDTO) {
        UserData userData = new UserData(userDTO);
        userRepository.save(userData);
        String token = tokenUtility.createToken(userData.getUserid());
        emailService.sendEmail(userDTO.getEmail(),"User created", "Hello you have successfully sign-up"+ userDTO);
        return token;
    }

    public UserData getUserById(String token){
        int userid = tokenUtility.decodeToken(token);
        return userRepository.findById(userid).orElseThrow(()->new CustomException("user id" + userid + "not found"));
    }
    //show all data in the DB post method using
    public List<UserData> listAll() {
        if(userRepository.findAll().isEmpty()){
            throw new CustomException("DB is Empty..!");
        }else
            return userRepository.findAll();
    }

    //delete by id from table
    public String deleteById(int userid) {
        if (userRepository.findById(userid).isPresent()) {
            userRepository.deleteById(userid);
            return "User ID: " + userid + " is Deleted Successfully!!";
        } else throw new CustomException("User ID not found..!");
    }

    //update by id
    public UserData editById(UserData userData, int userid) {
        if (userRepository.findById(userid).isPresent()) {
            UserData newData = new UserData(userid, userData);
            return userRepository.save(newData);
        }else
            throw new CustomException("User id not found..!");
    }


}
