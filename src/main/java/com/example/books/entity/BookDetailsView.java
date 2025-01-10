package com.example.books.entity;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Immutable
@Table(name="book_details_view")
public class BookDetailsView {
    @Id
    private Long bookId;
    private String bookTitle;
    private String author;
    private Double bookRating;

    protected BookDetailsView() {}

    public Long getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public Double getBookRating() {
        return bookRating;
    }
}
