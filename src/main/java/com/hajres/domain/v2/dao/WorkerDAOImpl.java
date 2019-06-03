package com.hajres.domain.v2.dao;

import com.hajres.domain.model.Worker;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkerDAOImpl implements WorkerDAO {

    @Autowired
    SessionFactory factory;

    @Override
    public List<Worker> getWorkerList() {
        return null;
    }

    @Override
    public Worker getWorker(int id) {
        return null;
    }

    @Override
    public void saveWorker(Worker worker) {

    }

    @Override
    public void deleteWorker(int id) {

    }
}
