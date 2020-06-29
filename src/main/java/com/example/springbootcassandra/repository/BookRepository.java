package com.example.springbootcassandra.repository;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.example.springbootcassandra.cache.SessionCache;
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

    @Autowired
    SessionCache sessionCache;

    public Book findById(int bookId, int bookVersion) {
        CqlMapper cqlMapper = new CqlMapper(bookId, bookVersion);
        String cql = cqlMapper.getCql();

        PreparedStatement preparedStatement = sessionCache.getPreparedStatement(cql);
        BoundStatement boundStatement = preparedStatement.bind(bookId, bookVersion);
        return cassandraAdminTemplate.selectOne(boundStatement, Book.class);
    }
}
