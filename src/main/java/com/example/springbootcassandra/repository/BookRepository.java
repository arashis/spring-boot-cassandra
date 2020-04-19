package com.example.springbootcassandra.repository;

import com.example.springbootcassandra.model.Book;
import com.example.springbootcassandra.model.BookKey;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Book.class, idClass = BookKey.class)
public interface BookRepository {
    @Query("select * from book where book_id=?0 and book_version=?1")
    Book findById(int bookId, int bookVersion);
}
