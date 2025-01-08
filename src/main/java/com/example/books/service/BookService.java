package com.example.books.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.books.entity.Book;
import com.example.books.repository.BookRepository;

@Service
public class BookService {

    private final BookRepository bookRepository;
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
      return bookRepository.findAll();
   }

   public Optional<Book> findBookById(Long id) {
    return bookRepository.findById(id);
   }
}
