package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.RequestEmployee;
import com.thoughtworks.springbootemployee.dto.ResponseEmployee;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Component
public class EmployeeMapper {
    public Employee toEmployee(RequestEmployee requestEmployee){
        if(isNull(requestEmployee)) {return null;}
        Employee employee =new Employee();
        BeanUtils.copyProperties(requestEmployee,employee);
        return employee;
    }

    public ResponseEmployee toResponseEmployee(Employee employee){
        if(isNull(employee)) {return null;}
        ResponseEmployee responseEmployee = new ResponseEmployee();
        BeanUtils.copyProperties(employee,responseEmployee);
        return responseEmployee;
    }

    public List<ResponseEmployee> toResponseEmployees(List<Employee> employees){
        if(isNull(employees)) {return null;}
        List<ResponseEmployee> responseEmployees = new ArrayList<>();
        employees.forEach(employee -> {
            responseEmployees.add(toResponseEmployee(employee));
        });
        return responseEmployees;
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
