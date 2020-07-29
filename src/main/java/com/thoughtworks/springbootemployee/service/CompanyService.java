package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getCompanies() {
        return companyRepository.getCompanies();
    }

    public List<Company> getCompaniesPage(int page, int pageSize) {
        return companyRepository.getCompaniesPage(page,pageSize);
    }

    public Company getCompany(int id) {
        return companyRepository.getCompany(id);
    }
}
