package com.spring_security.security_demo.api.controller;

import com.spring_security.security_demo.api.model.BookDto;
import com.spring_security.security_demo.api.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
public class Books {
    @Autowired
    private IBookRepository iBookRepository;

    @GetMapping
    @RequestMapping("{bookId}")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<BookDto> getBook(@PathVariable(value = "bookId") String bookId) {
        var book = iBookRepository.getBook(Integer.parseInt(bookId));
        return ResponseEntity.ok(book);
    }
}
