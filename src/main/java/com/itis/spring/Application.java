package com.itis.spring;

import com.itis.spring.config.AppConfig;
import com.itis.spring.model.User;
import com.itis.spring.model.builder.UserBuilder;
import com.itis.spring.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = appContext.getBean(UserService.class);
        User user = new UserBuilder()
                .setName("User")
                .setAge(23)
                .createUser();
        System.out.println(userService.save(user));
        userService.findAll().forEach(System.out::println);


    }
}
