package com.hajres.domain.v2.dao;

import com.hajres.PaginatedResult;
import com.hajres.domain.model.Worker;

import java.util.List;

public interface WorkerDAO {

    List<Worker> getWorkerList();

    Worker getWorker(int id);

    void saveWorker(Worker worker);

    void deleteWorker(int id);

    List<Worker> getWorkerList(String filter);

    PaginatedResult<Worker> getPaginatedWorkerList(int firstResult, int maxResult);

    PaginatedResult<Worker> getPaginatedWorkerList(String filter, int firstResult, int maxResult);
}
