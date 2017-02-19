package com.itis.spring.dao.impl;

import com.itis.spring.dao.UserDao;
import com.itis.spring.model.Auto;
import com.itis.spring.model.User;
import com.itis.spring.model.builder.AutoBuilder;
import com.itis.spring.model.builder.UserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    // language=SQL
    private final static String FIND_USER = "SELECT * FROM users WHERE users.id = ?";
    // language=SQL
    private final static String FIND_ALL_AUTO = "SELECT * FROM auto";
    // language=SQL
    private final static String FIND_ALL_USERS = "SELECT * FROM users ORDER BY users.id";
    // language=SQL
    private final static String DELETE_USER = "DELETE  FROM users WHERE users.id = ?";
    // language=SQL
    private final static String UPDATE_USER = "UPDATE  users SET age = ?, name = ? WHERE id = ?";
    // language=SQL
    private final static String SAVE_USER = "INSERT INTO users (age, name) VALUES (?,?)";
    private JdbcTemplate jdbcTemplate;
    private Map<Long, User> userMap;

    private RowMapper<User> userRowMapper = (resultSet, i) -> {
        User user = new UserBuilder()
                .setId(resultSet.getLong("id"))
                .setName(resultSet.getString("name"))
                .setAge(resultSet.getInt("age"))
                .setAutos(new ArrayList<Auto>())
                .createUser();
        userMap.put(user.getId(), user);
        return user;
    };

    private RowMapper<Auto> autoRowMapper = (resultSet, i) -> {
        Auto auto = new AutoBuilder()
                .setId(resultSet.getLong("id"))
                .setModel(resultSet.getString("model"))
                .setUser(userMap.get(resultSet.getLong("user_id")))
                .createAuto();
        userMap.get(auto.getUser().getId())
                .getAutos().add(auto);
        return auto;
    };

    public UserDaoImpl() {
    }

    @Autowired
    public UserDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        userMap = new HashMap<>();
    }

    public User find(Long id) {
        try {
            return jdbcTemplate.queryForObject(FIND_USER, new Object[]{id}, userRowMapper);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public void delete(Long id) {
        jdbcTemplate.update(DELETE_USER, id);
    }

    public Long save(User user) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    SAVE_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, user.getAge());
            preparedStatement.setString(2, user.getName());
            return preparedStatement;
        }, holder);
        return (Long) holder.getKeys().get("id");
    }

    public List<User> findAll() {
        jdbcTemplate.query(FIND_ALL_USERS, userRowMapper);
        jdbcTemplate.query(FIND_ALL_AUTO, autoRowMapper);
        return new ArrayList<User>(userMap.values());
    }

    public void update(User user) {
        jdbcTemplate.update(UPDATE_USER, user.getAge(), user.getName(), user.getId());
    }
}
