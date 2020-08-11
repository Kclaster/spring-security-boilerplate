package com.spring_security.security_demo.api.controller;

import com.spring_security.security_demo.api.model.BookDto;
import com.spring_security.security_demo.api.repository.IBookRepository;
import com.spring_security.security_demo.security.service.IUserService;
import com.spring_security.security_demo.security.util.JwtUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(Books.class)
public class BooksTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IUserService iUserService;
    @MockBean
    private IBookRepository iBookRepository;
    @MockBean
    private JwtUtil jwtUtil;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser(value = "spring")
    @Test
    public void shouldFetchBook() throws Exception {
        when(iBookRepository.getBook(1))
                .thenReturn(dummyResult());

        mvc.perform(get("/api/v1/books/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Hakuna Matata"));
    }

    private BookDto dummyResult() {
        BookDto mockBookDto = new BookDto();

        mockBookDto.setId(1);
        mockBookDto.setTitle("Hakuna Matata");
        mockBookDto.setDescription("It means no worries.");

        return mockBookDto;
    }
}

