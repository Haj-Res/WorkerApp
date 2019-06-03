package com.hajres.domain.v2.dao;

import com.hajres.domain.model.Address;

import java.util.List;

public interface AddressDAO {

    void saveAddress(Address address);

    List<Address> getAddress(Address address);
}
