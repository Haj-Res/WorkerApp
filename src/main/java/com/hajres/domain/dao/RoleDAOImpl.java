package com.hajres.domain.dao;

import com.hajres.domain.entity.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    private SessionFactory factory;

    @Override
    public Role findRoleByName(String name) {
        Session session = factory.getCurrentSession();

        Query<Role> query = session.createQuery("from Role where name=:name", Role.class);
        query.setParameter("name", name);

        Role role = null;

        try {
            role = query.getSingleResult();
        } catch (Exception e) {
            role = null;
        }
        return role;
    }
}
