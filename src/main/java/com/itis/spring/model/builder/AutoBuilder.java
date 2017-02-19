package com.itis.spring.model.builder;

import com.itis.spring.model.Auto;
import com.itis.spring.model.User;

public class AutoBuilder {

    private Long id;
    private String model;
    private User user;

    public AutoBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public AutoBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    public AutoBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public Auto createAuto() {
        return new Auto(id, model, user);
    }
}