package com.hajres.domain;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.sql.Date;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class WorkerDaoTest {

    @Test
    public void AddingWorkerWithNullAddress() {
        WorkerDao dao = new WorkerDao();
        Worker worker = GenerateWorkerWithoutCompanyAndAddress();

        assertEquals(dao.add(worker), 0);
    }
    @Test
    public void AddingWorkerWithAddress(){
        WorkerDao dao = new WorkerDao();
        Worker worker = GenerateWorkerWithoutCompanyAndAddress();
        worker.setAddress(GenerateAddress());

        assertNotEquals(dao.add(worker), 0);

    }

    private Worker GenerateWorkerWithoutCompanyAndAddress() {
        Worker worker = new Worker();

        worker.setJmbg("1234567891234");
        worker.setFirstName("Pero");
        worker.setLastName("Peric");
        worker.setBirthDate(new Date(89,6, 7));

        return worker;
    }

    private Address GenerateAddress() {
        Address address = new Address();
        address.setCity("Test City");
        address.setStreet("Test Street");
        address.setNumber("14b");

        return address;
    }
}