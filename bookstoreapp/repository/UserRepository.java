package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserData,Integer> {

    Optional<UserData> findByLoginid(String loginid);
}
