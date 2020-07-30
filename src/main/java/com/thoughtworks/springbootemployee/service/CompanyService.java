package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Exception.IllegalUpdateCompanyException;
import com.thoughtworks.springbootemployee.Exception.NoSuchCompanyException;
import com.thoughtworks.springbootemployee.constant.ExceptionMessage;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public Page<Company> getCompaniesPage(int page, int pageSize) {
        return companyRepository.findAll(PageRequest.of(page-1, pageSize));
    }

    public Company getCompany(int id) {
        return companyRepository.findById(id).orElse(null);
    }


    public List<Employee> getEmployees(int companyId){
        Company company = companyRepository.findById(companyId).orElse(null);
        if (company == null) {
            throw new NoSuchCompanyException(ExceptionMessage.NO_SUCH_EMPLOYEE.getErrorMsg());
        }
        return company.getEmployees();
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompany(int companyId, Company company){
        if (companyId != company.getId()) {
            throw new IllegalUpdateCompanyException(ExceptionMessage.ILLEGAL_UPDATE_COMPANY.getErrorMsg());
        }

        Company oldCompany = companyRepository.findById(companyId).orElse(null);
        if (oldCompany == null) {
            throw new NoSuchCompanyException(ExceptionMessage.NO_SUCH_COMPANY.getErrorMsg());
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

    public void deleteCompany(int companyId){
        Company oldCompany = companyRepository.findById(companyId).orElse(null);
        if (Objects.isNull(oldCompany)) {
            throw new NoSuchCompanyException(ExceptionMessage.NO_SUCH_COMPANY.getErrorMsg());
        }
        companyRepository.deleteById(companyId);
    }
}
