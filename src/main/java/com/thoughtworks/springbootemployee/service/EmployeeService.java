package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Exception.IllegalOperationException;
import com.thoughtworks.springbootemployee.Exception.NoSuchDataException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public List<Employee> queryEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> queryEmployeesByGender(String gender) {
        return employeeRepository.findAllByGender(gender);
    }

    public Page<Employee> queryEmployeesByPage(int currentPage, int pageSize) {
        return employeeRepository.findAll(PageRequest.of(currentPage, pageSize));
    }

    public Employee queryEmployee(int employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Integer id, Employee employee) throws IllegalOperationException, NoSuchDataException {
        if (!id.equals(employee.getId())) {
            throw new IllegalOperationException();
        }
        Employee oldEmployee = employeeRepository.findById(id).orElse(null);
        if (oldEmployee == null) {
            throw new NoSuchDataException();
        }
        if (employee.getName() != null) {
            oldEmployee.setName(employee.getName());
        }
        if (employee.getAge() > 0) {
            oldEmployee.setAge(employee.getAge());
        }
        if (employee.getGender() != null) {
            oldEmployee.setGender(employee.getGender());
        }
        if (employee.getSalary() != null) {
            oldEmployee.setSalary(employee.getSalary());
        }
        return employeeRepository.save(oldEmployee);
    }

    public void deleteEmployee(int employeeId) throws NoSuchDataException {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null) {
            throw new NoSuchDataException();
        }
        employeeRepository.deleteById(employeeId);
    }


}
