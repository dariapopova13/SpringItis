package com.itis.spring.config;

import com.itis.spring.dao.UserDao;
import com.itis.spring.dao.impl.jdbc.UserDaoJdbcImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class TestJdbcConfig {

    @Bean(name = "com.itis.spring.test.jdbc.user.dao")
    public UserDao userJdbcDao() {
        return new UserDaoJdbcImpl(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScripts("scripts/schema_jdbc.sql", "scripts/data_jdbc.sql")
                .build();
    }


}
