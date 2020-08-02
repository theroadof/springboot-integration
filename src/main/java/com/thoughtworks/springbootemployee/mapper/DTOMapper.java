package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.RequestCompany;
import com.thoughtworks.springbootemployee.dto.RequestEmployee;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Component
public class DTOMapper {

    public Employee toEmployee(RequestEmployee requestEmployee){
        if(isNull(requestEmployee)) return null;
        Employee employee =new Employee();
        BeanUtils.copyProperties(requestEmployee,employee);
        return employee;
    }

    public Company toCompany(RequestCompany requestCompany){
        if(isNull(requestCompany)) return null;
        Company company = new Company();
        BeanUtils.copyProperties(requestCompany,company);
        return company;
    }

    public RequestEmployee toResponseEmployee(Employee employee){
        if(isNull(employee)) return null;
        RequestEmployee responseEmployee = new RequestEmployee();
        BeanUtils.copyProperties(employee,responseEmployee);
        return responseEmployee;
    }

    public RequestCompany toResponseCompany(Company company){
        if(isNull(company)) return null;
        RequestCompany responseCompany = new RequestCompany();
        BeanUtils.copyProperties(company,responseCompany);
        return responseCompany;
    }

    public List<RequestCompany> toResponseCompanies(List<Company> companies){
        if(isNull(companies)) return null;
        List<RequestCompany> responseCompanies = new ArrayList<>();
        companies.forEach(company -> {
            responseCompanies.add(toResponseCompany(company));
        });
        return responseCompanies;
    }

    public List<RequestEmployee> toResponseEmployees(List<Employee> employees){
        if(isNull(employees)) return null;
        List<RequestEmployee> responseEmployees = new ArrayList<>();
        employees.forEach(employee -> {
            responseEmployees.add(toResponseEmployee(employee));
        });
        return responseEmployees;
    }

    public Page<RequestCompany> toResponseCompanyPage(Page<Company> companies){
        if (isNull(companies)) return null;
        List<RequestCompany> responseCompanies = new ArrayList<>();
        companies.forEach(company -> {
            responseCompanies.add(toResponseCompany(company));
        });
        return new PageImpl<RequestCompany>(responseCompanies);
    }

    public Page<RequestEmployee> toResponseEmployeePage(Page<Employee> employees){
        if (isNull(employees)) return null;
        List<RequestEmployee> responseEmployees = new ArrayList<>();
        employees.forEach(employee -> {
            responseEmployees.add(toResponseEmployee(employee));
        });
        return new PageImpl<RequestEmployee>(responseEmployees);
    }
}
