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

        System.out.println(userService.find(4L));
        System.out.println("Save user id: " + userService.save(user));
        System.out.println(userService.isRegistrated(48L));
        System.out.println(userService.isRegistrated(8L));
        userService.delete(7L);

        User rammstein = userService.find(4L);
        rammstein.setName("Till Lindemann");
        userService.update(rammstein);

        userService.findAll().forEach(System.out::println);

    }
}
