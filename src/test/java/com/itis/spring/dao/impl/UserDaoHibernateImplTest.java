package com.itis.spring.dao.impl;

import com.fasterxml.classmate.AnnotationConfiguration;
import com.itis.spring.config.TestHibernateConfig;
import com.itis.spring.dao.UserDao;
import com.itis.spring.model.User;
import com.itis.spring.model.builder.UserBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestHibernateConfig.class)
public class UserDaoHibernateImplTest {

    @Autowired
    @Qualifier(value = "com.itis.spring.test.hibernate.user.dao")
    UserDao userDao;

    @Test
    public void delete() throws Exception {
        userDao.delete(3L);
        for (User user : userDao.findAll()) {
            assertNotEquals(user.getId(), new Long(3));
        }
    }

    @Test
    public void save() throws Exception {
        Long id = userDao.save(new UserBuilder()
                .setName("Saved User")
                .setAge(55)
                .createUser());
        assertEquals(new Long(4), id);
    }

    @Test
    public void update() throws Exception {
        User user = userDao.find(1L);
        user.setName("Updated user");
        userDao.update(user);
        assertEquals("Updated user", userDao.find(1L).getName());
    }

    @Test
    public void find() throws Exception {
        assertEquals(userDao.find(2L).getName(), "user2");
    }

    @Test
    public void findAll() throws Exception {
        Long i = 1L;
        for (User user : userDao.findAll()) {
            assertEquals(i, user.getId());
            i = i + 1;
        }
        userDao.findAll().forEach(System.out::println);
    }
}