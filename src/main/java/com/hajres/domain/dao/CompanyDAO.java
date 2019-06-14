package com.hajres.domain.dao;

import com.hajres.PaginatedResult;
import com.hajres.domain.model.Company;

import java.util.List;

public interface CompanyDAO {

    List<Company> getCompanyList();

    Company getCompany(int id);

    List<Company> getCompany(Company company);

    void saveCompany(Company company);

    void deleteCompany(int id);

    List<Company> getCompanyList(String filter);

    PaginatedResult<Company> getPaginatedCompanyList(int firstResult, int maxResults);

    PaginatedResult<Company> getPaginatedCompanyList(String filter, int firstResult, int maxResults);
}
