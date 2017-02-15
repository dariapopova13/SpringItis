package com.itis.spring.service.impl;

import com.itis.spring.dao.UserDao;
import com.itis.spring.model.User;
import com.itis.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean isRegistrated(Long id) {
        return userDao.find(id) != null;
    }

    public User find(Long id) {
        return userDao.find(id);
    }

    public void delete(Long id) {
        userDao.delete(id);
    }

    public Long save(User user) {
        return userDao.save(user);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public void update(User user) {
        userDao.update(user);
    }
}
