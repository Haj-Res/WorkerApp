package com.hajres.domain.dao;

import com.hajres.domain.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory factory;

    Logger logger = LogManager.getLogger(getClass().getName());

    @Override
    public User findByUserName(String name) {
        Session session = factory.getCurrentSession();

        Query<User> query = session.createQuery("from User where username=:name", User.class);
        query.setParameter("name",  name);
        logger.info(query.getQueryString());
        logger.info(name);
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
