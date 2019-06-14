package com.hajres.domain.dao;

import com.hajres.PaginatedResult;
import com.hajres.domain.entity.Worker;

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
