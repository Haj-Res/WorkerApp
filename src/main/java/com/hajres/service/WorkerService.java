package com.hajres.service;

import com.hajres.PaginatedResult;
import com.hajres.domain.entity.Worker;

import java.util.List;

public interface WorkerService {

    List<Worker> getWorkerList();

    Worker getWorker(int id);

    void saveWorker(Worker worker);

    void deleteWorker(int id);

    List<Worker> getWorkerList(String filter);

    PaginatedResult<Worker> getPaginatedWorkerList(int page, int pageSize);

    PaginatedResult<Worker> getPaginatedWorkerList(int page, int pageSize, String filter);
}
