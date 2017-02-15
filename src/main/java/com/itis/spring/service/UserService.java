package com.itis.spring.service;

import com.itis.spring.model.User;

public interface UserService extends BaseService<User> {

    boolean isRegistrated(Long id);
}
