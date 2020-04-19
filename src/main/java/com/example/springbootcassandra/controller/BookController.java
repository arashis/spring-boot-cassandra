package com.example.springbootcassandra.controller;

import com.example.springbootcassandra.model.Book;
import com.example.springbootcassandra.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping(method = RequestMethod.GET, path = "/{bookId}/{bookVersion}")
    public ResponseEntity<Book> getBook(@PathVariable(value = "bookId") int bookId, @PathVariable(value = "bookVersion") int bookVersion) {
        return new ResponseEntity<>(bookService.getBook(bookId, bookVersion), HttpStatus.OK);
    }
}
