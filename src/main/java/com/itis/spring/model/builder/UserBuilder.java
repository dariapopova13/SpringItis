package com.itis.spring.model.builder;

import com.itis.spring.model.User;

public class UserBuilder {
    private Long id;
    private String name;
    private int age;

    public UserBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    public User createUser() {
        return new User(id, name, age);
    }
}