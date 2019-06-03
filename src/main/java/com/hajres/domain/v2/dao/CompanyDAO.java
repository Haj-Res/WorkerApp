package com.hajres.domain.v2.dao;

import com.hajres.domain.model.Company;

import java.util.List;

public interface CompanyDAO {

    List<Company> getCompanyList();

    Company getCompany(int id);

    List<Company> getCompany(Company company);

    void saveCompany(Company company);

    void deleteCompany(int id);

    List<Company> getCompanyList(String filter);
}
