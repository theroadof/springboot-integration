package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.RequestCompany;
import com.thoughtworks.springbootemployee.dto.RequestEmployee;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class DTOMapper {

    public Employee toEmployee(RequestEmployee requestEmployee){
        Employee employee =new Employee();
        BeanUtils.copyProperties(requestEmployee,employee);
        return employee;
    }

    public Company toCompany(RequestCompany requestCompany){
        Company company = new Company();
        BeanUtils.copyProperties(requestCompany,company);
        return company;
    }
}
