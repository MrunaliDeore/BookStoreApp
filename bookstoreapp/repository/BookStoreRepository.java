package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.entity.BookStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookStoreRepository extends JpaRepository<BookStoreEntity,Integer> {

    @Query(value = "select * from book_store_entity e where e.name = :bname", nativeQuery = true)
    List<BookStoreEntity> findByBookName(@Param("bname") String name);

    @Query(value = "select * from book_store_entity order by name ASC", nativeQuery = true)
    List<BookStoreEntity> sortByBookName(@Param("feild") String feild);

    @Query(value = "select * from book_store_entity order by price DESC", nativeQuery = true)
    List<BookStoreEntity> sortByBookPrice(@Param("feild") String feild);
}
