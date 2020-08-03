package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.IllegalUpdateCompanyException;
import com.thoughtworks.springbootemployee.exception.NoSuchCompanyException;
import com.thoughtworks.springbootemployee.constant.ExceptionMessage;
import com.thoughtworks.springbootemployee.dto.RequestCompany;
import com.thoughtworks.springbootemployee.mapper.DTOMapper;
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

    @Autowired
    private DTOMapper dtoMapper;


    public List<RequestCompany> getCompanies() {
        List<Company> companies = companyRepository.findAll();
        return dtoMapper.toResponseCompanies(companies);
    }

    public Page<RequestCompany> getCompaniesPage(int page, int pageSize) {
        return dtoMapper.toResponseCompanyPage(companyRepository.findAll(PageRequest.of(page-1, pageSize)));
    }

    public RequestCompany getCompany(int id) {
        return dtoMapper.toResponseCompany(companyRepository.findById(id).orElse(null));
    }


    public List<Employee> getEmployees(int companyId){
        Company company = companyRepository.findById(companyId).orElse(null);
        if (company == null) {
            throw new NoSuchCompanyException(ExceptionMessage.NO_SUCH_EMPLOYEE.getErrorMsg());
        }
        return company.getEmployees();
    }

    public RequestCompany createCompany(RequestCompany requestCompany) {
        Company company = dtoMapper.toCompany(requestCompany);
        return dtoMapper.toResponseCompany(companyRepository.save(company));
    }

    public RequestCompany updateCompany(int companyId, RequestCompany requestCompany){
        if (companyId != requestCompany.getId()) {
            throw new IllegalUpdateCompanyException(ExceptionMessage.ILLEGAL_UPDATE_COMPANY.getErrorMsg());
        }

        Company company = companyRepository.findById(companyId).orElse(null);
        if (company == null) {
            throw new NoSuchCompanyException(ExceptionMessage.NO_SUCH_COMPANY.getErrorMsg());
        }

        if (requestCompany.getCompanyName() != null) {
            company.setCompanyName(requestCompany.getCompanyName());
        }

        if (requestCompany.getEmployeeNumber() > 0) {
            company.setEmployeeNumber(requestCompany.getEmployeeNumber());
        }

        if (requestCompany.getEmployees().size() > 0) {
            company.setEmployees(requestCompany.getEmployees());
        }

        return dtoMapper.toResponseCompany(companyRepository.save(company));
    }

    public void deleteCompany(int companyId){
        Company oldCompany = companyRepository.findById(companyId).orElse(null);
        if (Objects.isNull(oldCompany)) {
            throw new NoSuchCompanyException(ExceptionMessage.NO_SUCH_COMPANY.getErrorMsg());
        }
        companyRepository.deleteById(companyId);
    }
}
