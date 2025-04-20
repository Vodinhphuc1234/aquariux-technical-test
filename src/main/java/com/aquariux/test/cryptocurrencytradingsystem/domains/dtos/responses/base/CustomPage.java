package com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.base;

import lombok.Data;

import java.util.List;

@Data
public class CustomPage<T> {

    private int pageNumber;
    private long totalElements;
    private int totalPages;
    private int size;
    private List<T> content;

    public CustomPage(int pageNumber, long totalElements, int totalPages, int size, List<T> content) {
        this.pageNumber = pageNumber;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.size = size;
        this.content = content;
    }
}