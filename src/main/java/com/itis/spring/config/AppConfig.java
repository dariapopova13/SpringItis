package com.itis.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.itis.spring")
@PropertySource("jdbc.properties")
@EnableJpaRepositories(basePackages = "com.itis.spring.repository")
public class AppConfig {

}
