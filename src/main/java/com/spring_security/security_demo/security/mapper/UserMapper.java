package com.spring_security.security_demo.security.mapper;

import com.spring_security.security_demo.security.authority.UserRole;
import com.spring_security.security_demo.security.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserMapper implements RowMapper<User> {
    public User mapRow(ResultSet rs, int i) throws SQLException {
        var user = new User(
                UUID.fromString(rs.getString("id")),
                rs.getString("username"),
                rs.getString("password"),
                UserRole.valueOf(rs.getInt("roleId")).get().getGrantedAuthorities(),
                rs.getBoolean("isExpired"),
                rs.getBoolean("isLocked"),
                rs.getBoolean("isCredentialsExpired"),
                rs.getBoolean("isEnabled"),
                rs.getInt("roleId"));

        return user;
    }
}