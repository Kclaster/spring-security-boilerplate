package com.spring_security.security_demo.api.repository;

import com.spring_security.security_demo.api.model.BookDto;

public interface IBookRepository {
    void createBook();
    BookDto getBook(Integer id);
}
