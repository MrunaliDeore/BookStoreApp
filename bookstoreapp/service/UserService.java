package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.LoginDTO;
import com.example.bookstoreapp.dto.UserDTO;
import com.example.bookstoreapp.email.EmailService;
import com.example.bookstoreapp.entity.Cart;
import com.example.bookstoreapp.entity.UserData;
import com.example.bookstoreapp.exception.CustomException;
import com.example.bookstoreapp.repository.UserRepository;
import com.example.bookstoreapp.util.TokenUtility;
import org.springframework.beans.factory.ObjectProvider;
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
        emailService.sendEmail(userDTO.getEmail(), "User created", "Hello you have successfully sign-up " + token);
        return token;
    }

    //login with id and pass
    public UserData loginUser(LoginDTO loginDTO) {
        Optional<UserData> login = userRepository.findByLoginid(loginDTO.getLoginid());
        if (loginDTO.getLoginid().equals(login.get().getLoginid()) && loginDTO.getPassword().equals(login.get().getPassword())) {
            System.out.println("Login Successfull..!");
            return login.get();
        } else {
            throw new CustomException("Login ID and Password is wrong");
        }
    }

    public UserData getUserById(String token){
        int id = tokenUtility.decodeToken(token);
        return userRepository.findById(id).orElseThrow(()->new CustomException("user id" + id + "not found"));
    }
    //show all data in the DB post method using
    public List<UserData> listAll() {
        if(userRepository.findAll().isEmpty()){
            throw new CustomException("DB is Empty..!");
        }else
            return userRepository.findAll();
    }

    //delete by id from table
    public String deleteById(int id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return "User ID: " + id + " is Deleted Successfully!!";
        } else throw new CustomException("User ID not found..!");
    }

    //update by id
    public UserData editById(UserData userData, int id) {
        if (userRepository.findById(id).isPresent()) {
            UserData newData = new UserData(id, userData);
            return userRepository.save(newData);
        }else
            throw new CustomException("User id not found..!");
    }


}