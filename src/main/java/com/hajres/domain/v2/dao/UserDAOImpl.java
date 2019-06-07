package com.hajres.domain.v2.dao;

import com.hajres.domain.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory factory;

    @Override
    public User findByUserName(String name) {
        Session session = factory.getCurrentSession();

        Query<User> query = session.createQuery("from User where username=:name", User.class);
        User user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            user = null;
        }
        return user;
    }

    @Override
    public void save(User user) {
        Session session = factory.getCurrentSession();
        session.saveOrUpdate(user);
    }
}
