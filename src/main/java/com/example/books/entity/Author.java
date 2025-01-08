package com.example.books.entity;

import com.example.books.entity.Book;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Book> books;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
