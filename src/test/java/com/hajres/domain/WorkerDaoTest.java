package com.hajres.domain;

import com.hajres.domain.dao.WorkerDao;
import com.hajres.domain.model.Address;
import com.hajres.domain.model.Company;
import com.hajres.domain.model.Worker;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WorkerDaoTest {


    // Application should not add worker if address isn't set
    // Throws SQLException
    @Test
    public void AddWorkerWithNullAddress() {
        WorkerDao dao = new WorkerDao();
        Worker worker = GenerateWorkerWithoutCompanyAndAddress("1111222233330");

        assertEquals(dao.add(worker), 0);
    }

    // Application should add worker with address but no company
    @Test
    public void AddWorkerWithAddress(){
        WorkerDao dao = new WorkerDao();
        Worker worker = GenerateWorkerWithoutCompanyAndAddress("1111222233331");
        worker.setAddress(GenerateAddress());

        assertNotEquals(dao.add(worker), 0);
    }

    // Application should add worker with company and address
    @Test
    public void AddWorkerWithAddressAndCompany() {
        WorkerDao dao = new WorkerDao();
        Worker worker = GenerateWorkerWithoutCompanyAndAddress("1111222233332");
        worker.setAddress(GenerateAddress());
        worker.setCompany(GenerateCompany());

        assertNotEquals(dao.add(worker), 0);
    }

    @Test
    public void FetchAllWorkerFromDatabase() {
        WorkerDao dao = new WorkerDao();
        List<Worker> workerList;
        workerList = dao.findAll();
        assertNotNull(workerList);
    }

    @Test
    public void FetchOneWorkerFromDatabase() {
        WorkerDao dao = new WorkerDao();
        Worker worker = dao.findById("1111222233332");
        assertNotNull(worker);
    }

    @Test
    public void FetchWorkerByNameFromDatabase() {
        WorkerDao dao = new WorkerDao();
        List<Worker> workerList = dao.findByName("hans");
        assertNotNull(workerList);
    }

    private Worker GenerateWorkerWithoutCompanyAndAddress(String jmbg) {
        Worker worker = new Worker();
        worker.setJmbg(jmbg);
        worker.setFirstName("Pero");
        worker.setLastName("Peric");
        worker.setLocalDateBirthDate(null);

        return worker;
    }

    private Address GenerateAddress() {
        Address address = new Address();
        address.setCity("Test City");
        address.setStreet("Test Street");
        address.setNumber("14b");

        return address;
    }

    private Company GenerateCompany() {
        Company company = new Company();
        company.setName("Test Company");
        company.setAddress(GenerateAddress());

        return company;
    }
}