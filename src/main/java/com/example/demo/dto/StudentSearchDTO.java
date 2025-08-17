package com.example.demo.dto;

public class StudentSearchDTO {

    private String query;

    public StudentSearchDTO() {}

    public StudentSearchDTO(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = (query != null && query.trim().isEmpty()) ? null : query;
    }

    @Override
    public String toString() {
        return "StudentSearchDTO{" +
                "query='" + query + '\'' +
                '}';
    }
}
