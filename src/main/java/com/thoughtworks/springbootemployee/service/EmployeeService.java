package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.ResponseEmployee;
import com.thoughtworks.springbootemployee.exception.IllegalUpdateEmployeeException;
import com.thoughtworks.springbootemployee.exception.NoSuchEmployeeException;
import com.thoughtworks.springbootemployee.constant.ExceptionMessage;
import com.thoughtworks.springbootemployee.dto.RequestEmployee;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
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
    private EmployeeMapper employeeMapper;

    public List<ResponseEmployee> queryEmployees() {
        return employeeMapper.toResponseEmployees(employeeRepository.findAll());
    }

    public List<ResponseEmployee> queryEmployeesByGender(String gender) {
        return employeeMapper.toResponseEmployees(employeeRepository.findAllByGender(gender));
    }

    public Page<ResponseEmployee> queryEmployeesByPage(int currentPage, int pageSize) {
        return employeeMapper.toResponseEmployeePage(employeeRepository.findAll(PageRequest.of(currentPage-1, pageSize)));
    }

    public ResponseEmployee queryEmployee(int employeeId) {
        return employeeMapper.toResponseEmployee(employeeRepository.findById(employeeId).orElse(null));
    }

    public ResponseEmployee createEmployee(RequestEmployee requestEmployee) {
        Employee employee = employeeMapper.toEmployee(requestEmployee);
        return employeeMapper.toResponseEmployee(employeeRepository.save(employee));
    }

    public ResponseEmployee updateEmployee(Integer id, RequestEmployee requestEmployee){
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
        return employeeMapper.toResponseEmployee(employeeRepository.save(employee));
    }

    public void deleteEmployee(int employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null) {
            throw new NoSuchEmployeeException(ExceptionMessage.NO_SUCH_EMPLOYEE .getErrorMsg());
        }
        employeeRepository.deleteById(employeeId);
    }

}
