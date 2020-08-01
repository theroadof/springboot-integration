package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Exception.IllegalUpdateEmployeeException;
import com.thoughtworks.springbootemployee.Exception.NoSuchEmployeeException;
import com.thoughtworks.springbootemployee.constant.ExceptionMessage;
import com.thoughtworks.springbootemployee.dto.RequestEmployee;
import com.thoughtworks.springbootemployee.mapper.DTOMapper;
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

    @Autowired
    private DTOMapper dtoMapper;

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

    public Employee createEmployee(RequestEmployee requestEmployee) {
        Employee employee = dtoMapper.toEmployee(requestEmployee);
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Integer id, RequestEmployee requestEmployee){
        if (!id.equals(requestEmployee.getId())) {
            throw new IllegalUpdateEmployeeException(ExceptionMessage.ILLEGAL_UPDATE_EMPLOYEE.getErrorMsg());
        }
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            throw new NoSuchEmployeeException(ExceptionMessage.NO_SUCH_EMPLOYEE.getErrorMsg());
        }
        if (requestEmployee.getName() != null) {
            employee.setName(requestEmployee.getName());
        }
        if (requestEmployee.getAge() > 0) {
            employee.setAge(requestEmployee.getAge());
        }
        if (requestEmployee.getGender() != null) {
            employee.setGender(requestEmployee.getGender());
        }
        if (requestEmployee.getSalary() != null) {
            employee.setSalary(requestEmployee.getSalary());
        }
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(int employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null) {
            throw new NoSuchEmployeeException(ExceptionMessage.NO_SUCH_EMPLOYEE .getErrorMsg());
        }
        employeeRepository.deleteById(employeeId);
    }

}
