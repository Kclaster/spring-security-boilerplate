package com.spring_security.security_demo.security.repository;

import com.spring_security.security_demo.api.helpers.SqlHelper;
import com.spring_security.security_demo.security.mapper.UserMapper;
import com.spring_security.security_demo.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements IUserRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    /* Field Autowireds are bad.
    Use constructor for final.
    Use setters for optional.
    Here is why: http://olivergierke.de/2013/11/why-field-injection-is-evil/
     */
    @Autowired
    public UserRepositoryImpl(@Qualifier("coreJdbcTemplate") JdbcTemplate jdbcTemplate,
                              NamedParameterJdbcTemplate namedParameterJdbcTemplate
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Optional<User> selectUserByUsername(String username) {
        String sql = SqlHelper.sql("select-auth-user");
        try {
            List<User> authUserEntities = jdbcTemplate.query(
                    sql,
                    new UserMapper(),
                    username
            );

            if (authUserEntities.size() == 1) {
                return Optional.of(authUserEntities.get(0));
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

}
