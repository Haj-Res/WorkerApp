package com.hajres.service;

import com.hajres.domain.model.Company;

import java.util.List;

public interface CompanyService {

    List<Company> getCompanyList();

    List<Company> getPaginatedCompanyList(int page);

    Company getCompany(int id);

    void saveCompany(Company company);

    void deleteCompany(int id);

    List<Company> getCompanyList(String filter);

    List<Company> getPaginatedCompanyList(int page, String filter);
}
