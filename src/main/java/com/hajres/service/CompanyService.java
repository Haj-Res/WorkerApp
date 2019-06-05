package com.hajres.service;

import com.hajres.PaginatedResult;
import com.hajres.domain.model.Company;

import java.util.List;

public interface CompanyService {

    List<Company> getCompanyList();

    PaginatedResult<Company> getPaginatedCompanyList(int page);

    Company getCompany(int id);

    void saveCompany(Company company);

    void deleteCompany(int id);

    List<Company> getCompanyList(String filter);

    PaginatedResult<Company> getPaginatedCompanyList(int page, String filter);
}
