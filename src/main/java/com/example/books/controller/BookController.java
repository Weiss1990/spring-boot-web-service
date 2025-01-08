package com.example.books.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.books.dto.ApiResponse;
import com.example.books.entity.Book;
import com.example.books.service.BookService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<?> getBookById(@RequestParam(required = false) Long id) {

        if (id == null) {
            List<Book> books = bookService.getBooks();
            ApiResponse<List<Book>> response = new ApiResponse<>(200, books);
            return ResponseEntity.ok(response);
        }

        Optional<Book> book = bookService.findBookById(id);
        if (book.isPresent()) {       
            ApiResponse<Optional<Book>> response = new ApiResponse<Optional<Book>>(200, book);

            return ResponseEntity.ok(response);
        } 

        ApiResponse<String> response = new ApiResponse<String>(404, "Book not found with id " + id);
        return ResponseEntity.ok(response);
    }
}
