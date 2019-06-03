package com.hajres.service;

import com.hajres.domain.model.Company;

import java.util.List;

public interface CompanyService {

    List<Company> getCompanyList();

    Company getCompany(int id);

    void saveCompany(Company company);

    void deleteCompany(int id);

    List<Company> getCompanyList(String filter);
}
