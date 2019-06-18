package com.hajres.domain.dao;

import com.hajres.domain.dto.EditUserDto;
import com.hajres.domain.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory factory;

    @Override
    public User findByUserName(String name) {
        Session session = factory.getCurrentSession();

        Query<User> query = session.createQuery("from User where username=:name", User.class);
        query.setParameter("name",  name);
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

    @Override
    public void update(EditUserDto userDto, User user) {
        Session session = factory.getCurrentSession();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        session.saveOrUpdate(user);
    }
}
