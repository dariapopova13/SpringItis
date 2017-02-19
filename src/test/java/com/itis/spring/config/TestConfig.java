package com.itis.spring.config;

import com.itis.spring.dao.impl.UserDaoImpl;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class TestConfig {

    @Bean
    public UserDaoImpl userDao() {
        return new UserDaoImpl(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabase database = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScripts("schema.sql", "data.sql")
                .build();
        return database;
    }
}
