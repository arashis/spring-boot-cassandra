package com.example.springbootcassandra.repository;

public class CqlMapper {
    private final int bookId;
    private final int bookVersion;

    public CqlMapper(int bookId, int bookVersion) {
        this.bookId = bookId;
        this.bookVersion = bookVersion;
    }

    public String getCql() {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from book where book_id = ? and book_version = ?");
        return sb.toString();
    }
}
