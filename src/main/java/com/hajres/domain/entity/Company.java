package com.hajres.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_company")
    private int idCompany;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    @Column(name = "name")
    private String name;

    @Valid
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_address")
    private Address address;

    @OneToMany(mappedBy = "company" ,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Worker> workers;

    public Company() {
    }

    public int getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(int idCompany) {
        this.idCompany = idCompany;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public void addWorker(Worker worker) {
        if (workers == null) {
            workers = new ArrayList<>();
        }
        workers.add(worker);
        worker.setCompany(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(100);
        sb.append("[ID:").append(idCompany).append(']').append(' ');
        sb.append(name).append(' ');
        if (address != null && address.getCity() != null) {
            sb.append(address);
        }
        return sb.toString();
    }
}
