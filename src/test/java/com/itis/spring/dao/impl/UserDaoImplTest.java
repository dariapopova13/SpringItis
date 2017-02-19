package com.itis.spring.dao.impl;

import com.itis.spring.config.TestConfig;
import com.itis.spring.dao.UserDao;
import com.itis.spring.model.User;
import com.itis.spring.model.builder.UserBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void delete() throws Exception {
        userDao.delete(3L);
        System.out.println("After deleting user with id = 3");
        userDao.findAll().forEach(System.out::println);
    }

    @Test
    public void save() throws Exception {
        userDao.save(new UserBuilder()
                .setName("Saved user")
                .setAge(55)
                .createUser());
        System.out.println("After saving user");
        userDao.findAll().forEach(System.out::println);
    }

    @Test
    public void update() throws Exception {
        User user = userDao.find(1L);
        user.setName("Updated user");
        userDao.update(user);
        System.out.println("After updating user  with id = 1");
        userDao.findAll().forEach(System.out::println);
    }

    @Test
    public void find() throws Exception {
        System.out.println("Aftre finding user with id = 1 \n" + userDao.find(1L));
    }

    @Test
    public void findAll() throws Exception {
        System.out.println("After finding all");
        userDao.findAll().forEach(System.out::println);
    }
}