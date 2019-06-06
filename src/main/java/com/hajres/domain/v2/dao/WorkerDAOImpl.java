package com.hajres.domain.v2.dao;

import com.hajres.PaginatedResult;
import com.hajres.domain.model.Worker;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.QEncoderStream;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Queue;

@Repository
public class WorkerDAOImpl implements WorkerDAO {

    @Autowired
    SessionFactory factory;

    @Override
    public List<Worker> getWorkerList() {
        Session session = factory.getCurrentSession();
        Query<Worker> query = session.createQuery("from Worker order by lastName", Worker.class);
        return query.getResultList();
    }

    @Override
    public List<Worker> getWorkerList(String filter) {
        filter = "%" + filter + "%";
        Session session = factory.getCurrentSession();
        Query<Worker> query = session.createQuery("from Worker where firstName like :filter or lastName like :filter or company.name like :filter order by lastName", Worker.class);
        query.setParameter("filter", filter);
        return query.getResultList();
    }

    @Override
    public Worker getWorker(int id) {
        Session session = factory.getCurrentSession();
        return session.get(Worker.class, id);
    }

    @Override
    public void saveWorker(Worker worker) {
        Session session = factory.getCurrentSession();
        session.saveOrUpdate(worker);
    }

    @Override
    public void deleteWorker(int id) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("delete from Worker where id =:workerId");
        query.setParameter("workerId", id);
        query.executeUpdate();
    }

    @Override
    public PaginatedResult<Worker> getPaginatedWorkerList(int firstResult, int maxResult) {
        PaginatedResult<Worker> paginatedResult = new PaginatedResult<>();
        Session session = factory.getCurrentSession();

        Long rowCount = session.createQuery("select count(id)from Worker", Long.class).getSingleResult();
        long pageCount = (rowCount - 1) / maxResult + 1;
        paginatedResult.setPageCount(pageCount);

        Query<Worker> query = session.createQuery("from Worker ", Worker.class);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResult);
        List<Worker> workerList = query.getResultList();
        paginatedResult.setResultList(workerList);

        return  paginatedResult;
    }

    @Override
    public PaginatedResult<Worker> getPaginatedWorkerList(String filter, int firstResult, int maxResult) {
        filter = "%" + filter + "%";
        PaginatedResult<Worker> paginatedResult = new PaginatedResult<>();
        Session session = factory.getCurrentSession();
        Query<Long> countQuery = session.createQuery("select count(id) from Worker " +
                "where firstName like :filter or lastName like :filter or company.name like :filter order by lastName", Long.class);
        countQuery.setParameter("filter", filter);
        long rowCount = countQuery.getSingleResult();
        long pageCount = (rowCount - 1) / maxResult + 1;
        paginatedResult.setPageCount(pageCount);

        Query<Worker> query = session.createQuery("from Worker " +
                "where firstName like :filter or lastName like :filter or company.name like :filter order by lastName", Worker.class);
        query.setParameter("filter", filter);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResult);
        List<Worker> workerList = query.getResultList();
        paginatedResult.setResultList(workerList);

        return paginatedResult;
    }
}
