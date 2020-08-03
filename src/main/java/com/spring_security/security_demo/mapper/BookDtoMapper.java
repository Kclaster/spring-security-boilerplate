package com.spring_security.security_demo.mapper;

import com.spring_security.security_demo.model.BookDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class BookDtoMapper implements RowMapper<BookDto> {
    public BookDto mapRow(ResultSet rs, int i) throws SQLException {
        BookDto bookDto = new BookDto();
        bookDto.setId(rs.getInt("id"));
        bookDto.setTitle(rs.getString("title"));
        bookDto.setDescription(rs.getString("description"));

        return bookDto;
    }
}
