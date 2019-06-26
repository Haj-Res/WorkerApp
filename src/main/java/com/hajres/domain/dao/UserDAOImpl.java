package com.hajres.domain.dao;

import com.hajres.domain.dto.EditUserDto;
import com.hajres.domain.entity.Country;
import com.hajres.domain.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

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
    public void update(EditUserDto userDto, User user, Country country) {
        Session session = factory.getCurrentSession();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setCountryPreference(country);
        StringBuilder categoryPreference = new StringBuilder();
        for (int i = 0; i < userDto.getCategory().length; i++) {
            categoryPreference.append(userDto.getCategory()[i]).append(",");
        }
        user.setCategoryPreference(categoryPreference.toString());
        session.saveOrUpdate(user);
    }
}
