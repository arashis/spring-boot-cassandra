package com.example.springbootcassandra.model;

import lombok.Getter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@Getter
@PrimaryKeyClass
public class BookKey {
    @PrimaryKeyColumn(name = "book_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private int bookId;

    @PrimaryKeyColumn(name = "book_version", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private int bookVersion;
}
