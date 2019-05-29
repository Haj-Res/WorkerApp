package com.hajres.domain.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Company {
    private int idCompany = 0;
    @NotNull(message = "is required")
    @Size(min=1, message = "is required")
    private String name;
    @Valid
    private Address address;

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
