package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> queryEmployees() {
        return employeeRepository.getEmployees();
    }

    public List<Employee> queryEmployeesByGender(String male) {
        return employeeRepository.getEmployeesByGender(male);
    }

    public List<Employee> queryEmployeesByPage(int currentPage, int pageSize) {
        return employeeRepository.getEmployeesByPage(currentPage, pageSize);
    }

    public Employee queryEmployee(int employeeId) {
        return employeeRepository.getEmployeeById(employeeId);
    }

}
