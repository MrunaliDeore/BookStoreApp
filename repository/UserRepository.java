package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserData,Integer> {
}
