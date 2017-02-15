package com.itis.spring.dao.impl;

import com.itis.spring.dao.UserDao;
import com.itis.spring.model.User;
import com.itis.spring.model.builder.UserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    // language=SQL
    private final static String FIND_USER = "SELECT * FROM users WHERE users.id = ?";
    // language=SQL
    private final static String FIND_ALL_USERS = "SELECT * FROM users ORDER BY users.id";
    // language=SQL
    private final static String DELETE_USER = "DELETE  FROM users WHERE users.id = ?";
    // language=SQL
    private final static String UPDATE_USER = "UPDATE  users SET age = ?, name = ? WHERE id = ?";
    // language=SQL
    private final static String SAVE_USER = "INSERT INTO users (age, name) VALUES (?,?)";
    private JdbcTemplate jdbcTemplate;
    private RowMapper<User> rowMapper = new RowMapper<User>() {
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            return new UserBuilder()
                    .setId(resultSet.getLong("id"))
                    .setName(resultSet.getString("name"))
                    .setAge(resultSet.getInt("age"))
                    .createUser();
        }
    };

    public UserDaoImpl() {
    }

    @Autowired
    public UserDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public User find(Long id) {
        try {
            return jdbcTemplate.queryForObject(FIND_USER, new Object[]{id}, rowMapper);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public void delete(Long id) {
        jdbcTemplate.update(DELETE_USER, id);
    }

    public Long save(User user) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    SAVE_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, user.getAge());
            preparedStatement.setString(2, user.getName());
            return preparedStatement;
        }, holder);
        return (Long) holder.getKeys().get("id");
    }

    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL_USERS, rowMapper);
    }

    public void update(User user) {
        jdbcTemplate.update(UPDATE_USER, user.getAge(), user.getName(), user.getId());
    }
}
