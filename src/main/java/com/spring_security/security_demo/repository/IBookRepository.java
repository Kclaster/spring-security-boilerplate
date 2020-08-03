package com.spring_security.security_demo.repository;

import com.spring_security.security_demo.model.BookDto;

public interface IBookRepository {
    void createBook();
    BookDto getBook(Integer id);
}
