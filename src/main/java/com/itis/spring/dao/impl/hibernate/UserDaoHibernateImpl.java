package com.itis.spring.dao.impl.hibernate;

import com.itis.spring.dao.UserDao;
import com.itis.spring.model.User;
import com.itis.spring.model.builder.UserBuilder;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository(value = "com.itis.spring.hibernate.user.dao")
@Transactional
public class UserDaoHibernateImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User find(Long id) {
        return (User) sessionFactory.openSession().get(User.class, id);
    }

    @Override
    public void delete(Long id) {
        sessionFactory.getCurrentSession().delete(
                new UserBuilder().setId(id).createUser()
        );
    }

    @Override
    public Long save(User user) {
        return (Long) sessionFactory.openSession().save(user);
    }

    @Override
    @SuppressWarnings("all")
    public List<User> findAll() {
        return sessionFactory.openSession()
                .createQuery("from " + User.class.getName()).list();
    }

    @Override
    public void update(User user) {
        sessionFactory.openSession().merge(user);
    }
}
