package com.itis.spring.service.impl;

import com.itis.spring.model.User;
import com.itis.spring.repository.UserRepository;
import com.itis.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserJpaService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isRegistrated(Long id) {
        return false;
    }

    @Override
    public User find(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public Long save(User user) {
        return userRepository.save(user).getId();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }
}
