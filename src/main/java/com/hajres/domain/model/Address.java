package com.hajres.domain.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Address {
    private int idAddress = 0;
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String city;
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String street;
    private String number;

    public Address() {
    }

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "[ID:" + idAddress + "] " +
                street + " " + number +
                ", " + city;
    }
}
