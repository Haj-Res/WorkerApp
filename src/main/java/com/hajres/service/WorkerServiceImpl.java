package com.hajres.service;

import com.hajres.PaginatedResult;
import com.hajres.domain.model.Address;
import com.hajres.domain.model.Company;
import com.hajres.domain.v2.dao.AddressDAO;
import com.hajres.domain.v2.dao.CompanyDAO;
import com.hajres.domain.v2.dao.WorkerDAO;
import com.hajres.domain.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {
    private static final int MAX_RESULT = 10;
    @Autowired
    WorkerDAO workerDAO;
    @Autowired
    AddressDAO addressDAO;
    @Autowired
    CompanyDAO companyDAO;

    @Override
    @Transactional
    public List<Worker> getWorkerList() {
        return workerDAO.getWorkerList();
    }

    @Override
    @Transactional
    public PaginatedResult<Worker> getPaginatedWorkerList(int page) {
        int firstResult = (page - 1) * MAX_RESULT;
        return workerDAO.getPaginatedWorkerList(firstResult, MAX_RESULT);
    }

    @Override
    @Transactional
    public Worker getWorker(int id) {
        return workerDAO.getWorker(id);
    }

    @Override
    @Transactional
    public void saveWorker(Worker worker) {
        // Save address if it doesn't exist
        List<Address> addressList = addressDAO.getAddress(worker.getAddress());
        if (addressList.size() == 0) {
            addressDAO.saveAddress(worker.getAddress());
        } else {
            worker.setAddress(addressList.get(0));
        }

        // if company has no name, no company is set
        if (worker.getCompany().getName() == null) {
            worker.setCompany(null);
        } else {
            // find if company address exists in DB
            addressList = addressDAO.getAddress(worker.getCompany().getAddress());
            if (addressList.size() == 0) {
                addressDAO.saveAddress(worker.getCompany().getAddress());
            } else {
                worker.getCompany().setAddress(addressList.get(0));
            }
            // find if company with such address and name exists in DB
            List<Company> companyList = companyDAO.getCompany(worker.getCompany());
            if (companyList.size() == 0) {
                companyDAO.saveCompany(worker.getCompany());
            } else {
                worker.setCompany(companyList.get(0));
            }
        }
        workerDAO.saveWorker(worker);
    }

    @Override
    @Transactional
    public void deleteWorker(int id) {
        workerDAO.deleteWorker(id);
    }

    @Override
    @Transactional
    public List<Worker> getWorkerList(String filter) {
        return workerDAO.getWorkerList(filter);
    }

    @Override
    @Transactional
    public PaginatedResult<Worker> getPaginatedWorkerList(int page, String filter) {
        int firstResult = (page-1) * MAX_RESULT;
        return workerDAO.getPaginatedWorkerList(filter, firstResult, MAX_RESULT);
    }
}
