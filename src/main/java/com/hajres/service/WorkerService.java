package com.hajres.service;

import com.hajres.PaginatedResult;
import com.hajres.domain.model.Worker;

import java.util.List;

public interface WorkerService {

    List<Worker> getWorkerList();

    Worker getWorker(int id);

    void saveWorker(Worker worker);

    void deleteWorker(int id);

    List<Worker> getWorkerList(String filter);

    PaginatedResult<Worker> getPaginatedWorkerList(int page);

    PaginatedResult<Worker> getPaginatedWorkerList(int page, String filter);
}
