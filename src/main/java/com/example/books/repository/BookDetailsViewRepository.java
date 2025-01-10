package com.example.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.books.entity.BookDetailsView;

public interface BookDetailsViewRepository extends JpaRepository<BookDetailsView, Long> {
    @Query("SELECT b FROM BookDetailsView b WHERE b.bookId = :id")
    BookDetailsView findDetailsByBookId(Long id);
}