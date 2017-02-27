package com.itis.spring.dao.impl.hibernate;

import com.itis.spring.dao.AutoDao;
import com.itis.spring.model.Auto;
import com.itis.spring.model.builder.AutoBuilder;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "com.itis.spring.hibernate.auto.dao")
public class AutoDaoHibernateImpl  implements AutoDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Auto find(Long id) {
        return (Auto) sessionFactory.openSession().get(Auto.class, id);
    }

    @Override
    public void delete(Long id) {
        sessionFactory.openSession().delete(
                new AutoBuilder().setId(id).createAuto()
        );
    }

    @Override
    public Long save(Auto auto) {
        return (Long) sessionFactory.openSession().save(auto);
    }

    @Override
    @SuppressWarnings("all")
    public List<Auto> findAll() {
        return sessionFactory.openSession()
                .createQuery("from " + Auto.class.getName()).list();
    }

    @Override
    public void update(Auto auto) {
        sessionFactory.openSession().merge(auto);
    }
}
