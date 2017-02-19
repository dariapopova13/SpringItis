package com.itis.spring.model.builder;

import com.itis.spring.model.Auto;
import com.itis.spring.model.User;

import java.util.Collection;

public class UserBuilder {

    private Long id;
    private String name;
    private int age;
    private Collection<Auto> autos;

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

    public UserBuilder setAutos(Collection<Auto> autos) {
        this.autos = autos;
        return this;
    }

    public User createUser() {
        return new User(id, name, age, autos);
    }
}