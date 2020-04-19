package com.example.springbootcassandra.model;

import lombok.Getter;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

import java.util.List;

@Getter
public class Book {
    @PrimaryKey
    private BookKey bookKey;

    @Column
    private String title;

    @Column
    private String description;

    @Column("authors")
    private List<Author> authors;
}