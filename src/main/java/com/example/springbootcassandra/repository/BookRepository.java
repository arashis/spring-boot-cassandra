package com.example.springbootcassandra.repository;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.example.springbootcassandra.model.Book;
import com.example.springbootcassandra.model.BookKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraAdminTemplate;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Component;

//@RepositoryDefinition(domainClass = Book.class, idClass = BookKey.class)
//public interface BookRepository {
//    @Query("select * from book where book_id=?0 and book_version=?1")
//    Book findById(int bookId, int bookVersion);
//}

@Component
public class BookRepository {

    @Autowired
    private CassandraAdminTemplate cassandraAdminTemplate;

    public Book findById(int bookId, int bookVersion) {
        Select.Where select = QueryBuilder.select().from("book")
                .where(QueryBuilder.eq("book_id", bookId))
                .and(QueryBuilder.eq("book_version", bookVersion));
        return cassandraAdminTemplate.selectOne(select, Book.class);
    }
}
