package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.RequestCompany;
import com.thoughtworks.springbootemployee.dto.RequestEmployee;
import com.thoughtworks.springbootemployee.dto.ResponseCompany;
import com.thoughtworks.springbootemployee.dto.ResponseEmployee;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

// todo
@Component
public class DTOMapper {

    public Employee toEmployee(RequestEmployee requestEmployee){
        if(isNull(requestEmployee)) {return null;}
        Employee employee =new Employee();
        BeanUtils.copyProperties(requestEmployee,employee);
        return employee;
    }

    public Company toCompany(RequestCompany requestCompany){
        if(isNull(requestCompany)) {return null;}
        Company company = new Company();
        BeanUtils.copyProperties(requestCompany,company);
        return company;
    }

    public ResponseEmployee toResponseEmployee(Employee employee){
        if(isNull(employee)) {return null;}
        ResponseEmployee responseEmployee = new ResponseEmployee();
        BeanUtils.copyProperties(employee,responseEmployee);
        return responseEmployee;
    }

    public ResponseCompany toResponseCompany(Company company){
        if(isNull(company)) {return null;}
        ResponseCompany responseCompany = new ResponseCompany();
        BeanUtils.copyProperties(company,responseCompany);
        return responseCompany;
    }

    public List<ResponseCompany> toResponseCompanies(List<Company> companies){
        if(isNull(companies)) {return null;}
        List<ResponseCompany> responseCompanies = new ArrayList<>();
        companies.forEach(company -> {
            responseCompanies.add(toResponseCompany(company));
        });
        return responseCompanies;
    }

    public List<ResponseEmployee> toResponseEmployees(List<Employee> employees){
        if(isNull(employees)) {return null;}
        List<ResponseEmployee> responseEmployees = new ArrayList<>();
        employees.forEach(employee -> {
            responseEmployees.add(toResponseEmployee(employee));
        });
        return responseEmployees;
    }

    public Page<ResponseCompany> toResponseCompanyPage(Page<Company> companies){
        if (isNull(companies)) {return null;}
        List<ResponseCompany> responseCompanies = new ArrayList<>();
        companies.forEach(company -> {
            responseCompanies.add(toResponseCompany(company));
        });
        return new PageImpl<ResponseCompany>(responseCompanies);
    }

    public Page<ResponseEmployee> toResponseEmployeePage(Page<Employee> employees){
        if (isNull(employees)) {return null;}
        List<ResponseEmployee> responseEmployees = new ArrayList<>();
        employees.forEach(employee -> {
            responseEmployees.add(toResponseEmployee(employee));
        });
        return new PageImpl<ResponseEmployee>(responseEmployees);
    }
}
