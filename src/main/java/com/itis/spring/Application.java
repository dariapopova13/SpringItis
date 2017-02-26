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
        System.out.println("Saved user id: " + userService.save(user));
//        userService.delete(7L);

        User rammstein = userService.find(1L);
        rammstein.setName("Тилль Линдеманн");
        userService.update(rammstein);
        userService.findAll().forEach(System.out::println);

        System.out.println("Sorted by user name lenght");
        userService.findAll()
                .stream()
                .sorted((user1, user2)
                        -> Integer.compare(user1.getName().length(), user2.getName().length()))
                .forEach(System.out::println);

        System.out.println("Sorted by user name age");
        userService.findAll()
                .stream()
                .sorted((user1, user2)
                        -> Integer.compare(user1.getAge(), user2.getAge()))
                .forEach(System.out::println);


    }
}
