package com.hajres.service;

import com.hajres.domain.v2.dao.WorkerDAO;
import com.hajres.domain.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {
    @Autowired
    WorkerDAO workerDAO;

    @Override
    @Transactional
    public List<Worker> getWorkerList() {
        return workerDAO.getWorkerList();
    }

    @Override
    @Transactional
    public Worker getWorker(int id) {
        return workerDAO.getWorker(id);
    }

    @Override
    @Transactional
    public void saveWorker(Worker worker) {
        workerDAO.saveWorker(worker);
    }

    @Override
    @Transactional
    public void deleteWorker(int id) {
        workerDAO.deleteWorker(id);
    }
}
