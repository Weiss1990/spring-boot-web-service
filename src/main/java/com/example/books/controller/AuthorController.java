package com.example.books.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.books.dto.ApiResponse;
import com.example.books.entity.Author;
import com.example.books.service.AuthorService;

import java.util.List;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
       this.authorService = authorService;     
    }

    @GetMapping("/authors") 
    public ResponseEntity<ApiResponse<List<Author>>> getAuthors() {
        List<Author> authors = authorService.getAuthors();
        ApiResponse<List<Author>> response = new ApiResponse<>(200, authors);
        return ResponseEntity.ok(response);
    }
}
