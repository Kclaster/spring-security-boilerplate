package com.spring_security.security_demo.repository;

import com.spring_security.security_demo.helpers.SqlHelper;
import com.spring_security.security_demo.mapper.BookDtoMapper;
import com.spring_security.security_demo.model.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BookRepositoryImpl implements IBookRepository {
    @Autowired
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public BookRepositoryImpl(@Qualifier("coreJdbcTemplate")org.springframework.jdbc.core.JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public void createBook() {
        String sql = SqlHelper.sql("insert-coordinator");


        var params = new HashMap<String, Object>();
        params.put("title", "Lord of the Rings");
        params.put("description", "9 men go on an adventure to return another man's jewelry.");

        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public BookDto getBook(Integer bookId) {
        Map<String, Object> params = Map.of(
                "bookId", bookId
        );

        String sql = SqlHelper.sql("select-book");
        var books = namedParameterJdbcTemplate.query(
                sql,
                params,
                new BookDtoMapper()
        );

        if (books.size() == 1) {
            return books.get(0);
        }

        return null;
    }

}

