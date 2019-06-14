package com.hajres.service;

import com.hajres.PaginatedResult;
import com.hajres.domain.model.Address;
import com.hajres.domain.model.Company;
import com.hajres.domain.v2.dao.AddressDAO;
import com.hajres.domain.v2.dao.CompanyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyDAO companyDAO;
    @Autowired
    AddressDAO addressDAO;

    @Override
    @Transactional
    public List<Company> getCompanyList() {
        return companyDAO.getCompanyList();
    }

    @Override
    @Transactional
    public PaginatedResult<Company> getPaginatedCompanyList(int page, int pageSize) {
        int firstResult = (page - 1) * pageSize;
        return companyDAO.getPaginatedCompanyList(firstResult, pageSize);
    }

    @Override
    @Transactional
    public Company getCompany(int id) {
        return companyDAO.getCompany(id);
    }

    @Override
    @Transactional
    public void saveCompany(Company company) {
        List<Address> addressList = addressDAO.getAddress(company.getAddress());
        if (addressList.size() == 0) {
            addressDAO.saveAddress(company.getAddress());
        } else {
            company.setAddress(addressList.get(0));
        }
        companyDAO.saveCompany(company);

    }

    @Override
    @Transactional
    public void deleteCompany(int id) {
        companyDAO.deleteCompany(id);

    }

    @Override
    @Transactional
    public List<Company> getCompanyList(String filter) {
        return companyDAO.getCompanyList(filter);
    }

    @Override
    @Transactional
    public PaginatedResult<Company> getPaginatedCompanyList(int page, int pageSize, String filter) {
        int firstResult = (page - 1) * pageSize;
        return  companyDAO.getPaginatedCompanyList(filter, firstResult, pageSize);
    }
}
