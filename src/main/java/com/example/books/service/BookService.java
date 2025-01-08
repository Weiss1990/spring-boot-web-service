package com.example.books.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
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

   public Book addBook(Book book) {
        return bookRepository.save(book);
   }

   public Book updateBook(Long id, Book updatedBook) {
        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        return bookRepository.save(existingBook);
   }

   public Book partialUpdateBook(Long id, Map<String, Object> updates) {
        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        updates.forEach((key, value) -> {
            try {
                Field field = Book.class.getDeclaredField(key);
                field.setAccessible(true);
                field.set(existingBook, value);
                
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("Error updating field: " + key);
            }
        });

        return bookRepository.save(existingBook);
   }

   public void removeBook(Long id) {
        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        bookRepository.delete(existingBook);
   }
}
