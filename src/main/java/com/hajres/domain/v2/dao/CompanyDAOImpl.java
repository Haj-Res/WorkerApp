package com.hajres.domain.v2.dao;

import com.hajres.PaginatedResult;
import com.hajres.domain.model.Company;
import org.hibernate.Criteria;
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
    public void deleteCompany(int id) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("delete from Company where idCompany = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Company> getCompanyList(String filter) {
        filter = "%" + filter + "%";
        Session session = factory.getCurrentSession();
        Query<Company> query = session.createQuery("from Company where name like :filter or address.city like :filter or address.street like :filter", Company.class);
        query.setParameter("filter", filter);
        return query.getResultList();
    }

    @Override
    public PaginatedResult<Company> getPaginatedCompanyList(int firstResult, int maxResults) {
        PaginatedResult<Company> paginatedResult = new PaginatedResult<>();
        Session session = factory.getCurrentSession();

        Long rowCount = session.createQuery("select count(idCompany) from Company ", Long.class).getSingleResult();
        long pageCount = (rowCount - 1) / maxResults + 1;
        paginatedResult.setPageCount(pageCount);

        Query<Company> query = session.createQuery("from Company", Company.class);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);
        List<Company> companyList = query.getResultList();
        paginatedResult.setResultList(companyList);

        return paginatedResult;
    }

    @Override
    public PaginatedResult<Company> getPaginatedCompanyList(String filter, int firstResult, int maxResults) {
        PaginatedResult<Company> paginatedResult = new PaginatedResult<>();
        Session session = factory.getCurrentSession();

        Query<Long> countQuery = session.createQuery("select count(idCompany) from Company where name like :filter or address.city like :filter or address.street like :filter", Long.class);
        countQuery.setParameter("filter", filter);
        long rowCount = countQuery.getSingleResult();
        long pageCount = (rowCount - 1) / maxResults + 1;
        paginatedResult.setPageCount(pageCount);

        Query<Company> query = session.createQuery("from Company where name like :filter or address.city like :filter or address.street like :filter", Company.class);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);
        List<Company> companyList = query.getResultList();
        paginatedResult.setResultList(companyList);

        return paginatedResult;
    }
}
