package com.hajres.domain.model;

public class Address {
    private int idAddress = 0;
    private String city;
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
        StringBuilder sb = new StringBuilder(100);
        sb.append("[ID:").append(idAddress).append("] ");
        sb.append(street).append(" ").append(number);
        sb.append(", ").append(city);
        return  sb.toString();
    }
}
