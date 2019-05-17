package com.hajres.domain.model;

import java.sql.Date;
import java.time.LocalDate;

public class Worker {
    private String jmbg;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Company company;
    private Address address;

    public Worker() {
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getLocalDateBirthDate() {
        return birthDate;
    }

    public Date getBirthDate() {
        return Date.valueOf(birthDate);
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(150);
        sb.append("JMBG: ").append(jmbg).append(", ");
        sb.append("Name: ").append(firstName).append(" ").append(lastName).append(", ");
        sb.append("Born on: ").append(birthDate).append(", ");
        sb.append("Address: ").append(address);
        if (company.getIdCompany() != 0) {
            sb.append(", ").append("Employed at ").append(company);
        }
        return sb.toString();
    }
}
