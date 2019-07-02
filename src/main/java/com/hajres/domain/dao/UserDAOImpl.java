package com.hajres.domain.dao;

import com.hajres.PaginatedResult;
import com.hajres.domain.dto.UserDisplayDTO;
import com.hajres.domain.entity.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory factory;

    @Override
    public User findByUserName(String name) {
        Session session = factory.getCurrentSession();

        Query<User> query = session.createQuery("from User where username=:name", User.class);
        query.setParameter("name", name);
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
    public PaginatedResult<UserDisplayDTO> findAllPaginatedUser(int pageSize, int page) {
        Session session = factory.getCurrentSession();

        int firstResult = (page - 1) * pageSize;
        Long rowCount = session.createQuery("select count(username) from User", Long.class).getSingleResult();
        if (firstResult > rowCount) {
            firstResult = rowCount.intValue() - pageSize - 1;
        }
        PaginatedResult<UserDisplayDTO> result = new PaginatedResult<>();
        Query<User> query = session.createQuery("from User", User.class).setFirstResult(firstResult).setMaxResults(pageSize);
        List<User> list = query.getResultList();
        list.forEach(elem -> {
            result.getResultList().add(UserDisplayDTO.map(elem));
        });

        result.setPageCount((rowCount - 1) / pageSize + 1);
        return result;
    }

    @Override
    public void delete(String username) {
        Session session = factory.getCurrentSession();
        session.createQuery("delete from User where username = :username")
                .setParameter("username", username)
                .executeUpdate();
    }
}
