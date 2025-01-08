package com.example.books.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.books.entity.Author;
import com.example.books.repository.AuthorRepository;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    public List<Author> getAuthors() {
      System.out.println("Authors list: " + authorRepository.findAll());  
      return authorRepository.findAll();
   }
}
