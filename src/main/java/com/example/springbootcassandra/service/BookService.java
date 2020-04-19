package com.example.springbootcassandra.service;

import com.example.springbootcassandra.model.Book;
import com.example.springbootcassandra.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Book getBook(int bookId, int bookVersion) {
        return bookRepository.findById(bookId, bookVersion);
    }
}
