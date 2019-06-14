package com.hajres.domain.dao;

import com.hajres.domain.entity.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressDAOImpl implements AddressDAO {
    @Autowired
    SessionFactory factory;

    @Override
    public void saveAddress(Address address) {
        Session session = factory.getCurrentSession();
        session.save(address);
    }

    @Override
    public List<Address> getAddress(Address address) {
        Session session = factory.getCurrentSession();
        Query<Address> query = session.createQuery("from Address  where city = :city and street = :street and number = :number", Address.class);
        query.setParameter("city", address.getCity());
        query.setParameter("street", address.getStreet());
        query.setParameter("number", address.getNumber());
        return query.getResultList();
    }
}
