package com.hajres.domain.dao;

import com.hajres.domain.entity.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CountryDAOImpl implements CountryDAO {

    @Autowired
    private SessionFactory factory;
    @Override
    public List<Country> findAll() {
        Session session = factory.getCurrentSession();
        Query<Country> query = session.createQuery("from Country", Country.class);
        return query.getResultList();
    }

    @Override
    public Country findById(String id) {
        Session session = factory.getCurrentSession();
        return session.get(Country.class, id);
    }
}
