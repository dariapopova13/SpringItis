package com.itis.spring.dao.impl;

import com.itis.spring.config.TestHibernateConfig;
import com.itis.spring.model.Auto;
import com.itis.spring.model.User;
import com.itis.spring.model.builder.AutoBuilder;
import com.itis.spring.model.builder.UserBuilder;
import com.itis.spring.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestHibernateConfig.class)
public class UserDaoHibernateImplTest {

    //    @Autowired
//    @Qualifier(value = "com.itis.spring.test.hibernate.user.dao")
//    UserDao userService;
    @Autowired
    UserService userService;

    @Test
    public void delete() throws Exception {
        userService.delete(3L);
        for (User user : userService.findAll()) {
            assertNotEquals(user.getId(), new Long(3));
        }
    }

    @Test
    public void save() throws Exception {
        User expectedUser = new UserBuilder()
                .setName("Saved User")
                .setAge(55)
                .setAutos(new ArrayList<>())
                .createUser();
        Long id = userService.save(expectedUser);
        User actualUser = userService.find(id);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void update() throws Exception {
        User actualUser = userService.find(1L);
        actualUser.setName("Updated user");
        userService.update(actualUser);

        User expectedUser = new UserBuilder()
                .setId(1L)
                .setName("Updated user")
                .setAge(25)
                .createUser();
        List<Auto> autos = new ArrayList<>();
        autos.add(new AutoBuilder()
                .setId(2L)
                .setModel("Model2")
                .setUser(expectedUser)
                .createAuto());

        expectedUser.setAutos(autos);
        assertEquals(actualUser, expectedUser);
    }

    @Test
    public void find() throws Exception {

        User expectedUser = new UserBuilder()
                .setId(2L)
                .setName("user2")
                .setAge(35)
                .createUser();
        List<Auto> autos = new ArrayList<>();
        autos.add(new AutoBuilder()
                .setId(3L)
                .setModel("Model3")
                .setUser(expectedUser)
                .createAuto());
        expectedUser.setAutos(autos);
        User actualUser = userService.find(2L);
        assertEquals(actualUser, expectedUser);
    }

    @Test
    public void findAll() throws Exception {
        userService.findAll().forEach(System.out::println);
    }
}