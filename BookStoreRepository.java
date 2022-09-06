package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.entity.BookStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookStoreRepository extends JpaRepository<BookStoreEntity,Integer> {
}
