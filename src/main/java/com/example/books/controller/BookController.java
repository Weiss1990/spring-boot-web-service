package com.example.books.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.books.dto.ApiResponse;
import com.example.books.entity.Book;
import com.example.books.entity.BookDetailsView;
import com.example.books.service.BookService;

import java.util.List;
import java.util.Map;
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

    @GetMapping("/{id}/details")
    public ResponseEntity<?> getBookDetails(@PathVariable Long id) {
        try {
            BookDetailsView details = bookService.getBookDetails(id);
            ApiResponse<BookDetailsView> response = new ApiResponse<BookDetailsView>(200, details);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<?> response = new ApiResponse<>(500, e);
            return ResponseEntity.ok(response);
        } 
        
    }

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        Book createdBook = bookService.addBook(book);
        ApiResponse<Book> response = new ApiResponse<>(200, createdBook);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Book bookAfterUpdate = bookService.updateBook(id, updatedBook);
        ApiResponse<Book> response = new ApiResponse<>(200, bookAfterUpdate);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdateBook(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Book bookAfterPartialUpdate = bookService.partialUpdateBook(id, updates);
        ApiResponse<Book> response = new ApiResponse<>(200, bookAfterPartialUpdate);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeBook(@PathVariable Long id) {
        bookService.removeBook(id);
        ApiResponse<String> response = new ApiResponse<>(200, "Removed book with id: " + id);
        return ResponseEntity.ok(response);
    }

}
