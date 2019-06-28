package com.hajres.domain.dao;

import com.hajres.domain.entity.news.Country;

import java.util.List;

public interface CountryDAO {
    List<Country> findAll();

    Country findById(String id);
}
