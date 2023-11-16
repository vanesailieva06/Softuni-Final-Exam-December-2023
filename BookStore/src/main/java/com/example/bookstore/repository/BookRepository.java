package com.example.bookstore.repository;

import com.example.bookstore.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);
    Optional<Book> findByAddedInCartIsTrue();

    List<Book> findAllByAddedInCartIsTrue();

    Optional<Book> findByIdAndAddedInCartIsTrue(Long id);


    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.addedInCart=true WHERE b=:book")
    void updateBookAddedInCart
            (@Param(value = "book") Book book);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.favourite=true WHERE b=:book")
    void updateBookAddedInFavourites
            (@Param(value = "book") Book book);


    List<Book> findAllByFavouriteIsTrue();
}
