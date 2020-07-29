package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Exception.IllegalOperationException;
import com.thoughtworks.springbootemployee.Exception.NoSuchDataException;
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


    public List<Employee> getEmployees(int companyId) throws NoSuchDataException {
        Company company = companyRepository.findById(companyId).orElse(null);
        if (company == null) {
            throw new NoSuchDataException();
        }
        return company.getEmployees();
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompany(int companyId, Company company) throws IllegalOperationException, NoSuchDataException {
        if (companyId != company.getId()) {
            throw new IllegalOperationException();
        }

        Company oldCompany = companyRepository.findById(companyId).orElse(null);
        if (oldCompany == null) {
            throw new NoSuchDataException();
        }

        if (company.getCompanyName() != null) {
            oldCompany.setCompanyName(company.getCompanyName());
        }

        if (company.getEmployeeNumber() > 0) {
            oldCompany.setEmployeeNumber(company.getEmployeeNumber());
        }

        if (company.getEmployees().size() > 0) {
            oldCompany.setEmployees(company.getEmployees());
        }

        return companyRepository.save(oldCompany);
    }
}
