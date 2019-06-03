package com.hajres.domain.v2.dao;

import com.hajres.domain.model.Worker;

import java.util.List;

public interface WorkerDAO {

    List<Worker> getWorkerList();

    Worker getWorker(int id);

    void saveWorker(Worker worker);

    void deleteWorker(int id);
}
