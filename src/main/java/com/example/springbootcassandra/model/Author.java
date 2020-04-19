package com.example.springbootcassandra.model;

import lombok.Getter;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@UserDefinedType
@Getter
public class Author {
    @Column("name")
    private String name;

    @Column("description")
    private String description;
}
