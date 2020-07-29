package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public Page<Company> getCompaniesPage(int page, int pageSize) {
        return companyRepository.findAll(PageRequest.of(page, pageSize));
    }

    public Company getCompany(int id) {
        return companyRepository.findById(id).orElse(null);
    }


    public List<Employee> getEmployees(int companyId) {
        return null;
    }
}
