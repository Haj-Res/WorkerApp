package com.hajres.domain.v2.dao;

import com.hajres.domain.model.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyDAOImpl implements CompanyDAO {
    @Autowired
    SessionFactory factory;

    @Override
    public List<Company> getCompanyList() {
        Session session = factory.getCurrentSession();
        Query<Company> query = session.createQuery("from Company", Company.class);
        return query.getResultList();
    }

    @Override
    public Company getCompany(int id) {
        Session session = factory.getCurrentSession();
        return session.get(Company.class, id);
    }

    @Override
    public List<Company> getCompany(Company company) {
        Session session = factory.getCurrentSession();
        Query<Company> query = session.createQuery("from Company where name = :name and address = :address", Company.class);
        query.setParameter("name", company.getName());
        query.setParameter("address", company.getAddress());
        return query.getResultList();
    }

    @Override
    public void saveCompany(Company company) {
        Session session = factory.getCurrentSession();
        session.saveOrUpdate(company);
    }

    @Override
    public void deleteCompany(Company company) {

    }
}
