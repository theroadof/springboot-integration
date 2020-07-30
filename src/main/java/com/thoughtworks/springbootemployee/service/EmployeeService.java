package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Exception.IllegalUpdateEmployeeException;
import com.thoughtworks.springbootemployee.Exception.NoSuchEmployeeException;
import com.thoughtworks.springbootemployee.constant.ExceptionMessage;
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
        return employeeRepository.findAll(PageRequest.of(currentPage-1, pageSize));
    }

    public Employee queryEmployee(int employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Integer id, Employee employee){
        if (!id.equals(employee.getId())) {
            throw new IllegalUpdateEmployeeException(ExceptionMessage.ILLEGAL_UPDATE_EMPLOYEE.getErrorMsg());
        }
        Employee oldEmployee = employeeRepository.findById(id).orElse(null);
        if (oldEmployee == null) {
            throw new NoSuchEmployeeException(ExceptionMessage.NO_SUCH_EMPLOYEE.getErrorMsg());
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

    public void deleteEmployee(int employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null) {
            throw new NoSuchEmployeeException(ExceptionMessage.NO_SUCH_EMPLOYEE .getErrorMsg());
        }
        employeeRepository.deleteById(employeeId);
    }

}
