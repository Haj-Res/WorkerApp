package com.hajres.domain.v2.dao;

import com.hajres.domain.model.Worker;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        Query query =session.createQuery("delete from Worker where id =:workerId", Worker.class);
        query.setParameter("workerId", id);
        query.executeUpdate();
    }
}
