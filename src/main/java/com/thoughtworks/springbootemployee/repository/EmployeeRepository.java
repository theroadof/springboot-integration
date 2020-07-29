package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.utils.PageUtils;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {

    private static List<Employee> employees;

    static {
        employees = new ArrayList<>();
        employees.add(new Employee(1, "xiaoyi", 18, "Male", new BigDecimal(3000)));
        employees.add(new Employee(2, "xiaoer", 18, "Male", new BigDecimal(3000)));
        employees.add(new Employee(3, "xiaosan", 19, "Male", new BigDecimal(3000)));
        employees.add(new Employee(4, "xiaosi", 19, "Male", new BigDecimal(3000)));
        employees.add(new Employee(5, "xiaowu", 20, "Male", new BigDecimal(3000)));
        employees.add(new Employee(6, "xiaoliu", 20, "Female", new BigDecimal(3000)));
        employees.add(new Employee(7, "xiaoqi", 21, "Female", new BigDecimal(3000)));
        employees.add(new Employee(8, "xiaoba", 21, "Female", new BigDecimal(3000)));
        employees.add(new Employee(9, "xiaojiu", 18, "Male", new BigDecimal(3000)));
        employees.add(new Employee(10, "xiaoshi", 18, "Male", new BigDecimal(3000)));
    }

    public List<Employee> getEmployees() {
        return employees;
    }


    public List<Employee> getEmployeesByGender(String male) {
        return employees.stream().filter(employee -> employee.getGender().equals(male)).collect(Collectors.toList());
    }

    public List<Employee> getEmployeesByPage(int currentPage, int pageSize) {
        return new PageUtils<Employee>().getPage(employees, currentPage, pageSize);
    }

    public Employee getEmployeeById(int employeeId) {
        return employees.stream().filter(employee -> employee.getId() == employeeId).findFirst().orElse(null);
    }

    public Employee createEmployee(Employee employee) {
        if (!employees.contains(employee)) {
            employees.add(employee);
            return employee;
        }

        return null;
    }

    public Employee updateEmployee(Employee employeeUpdated) {
        Employee oldEmployee = employees.stream().filter(employee -> employee.getId()==employeeUpdated.getId()).findFirst().orElse(null);
        if (Objects.nonNull(oldEmployee)){
            employees.remove(oldEmployee);
            employees.add(employeeUpdated);
            return employeeUpdated;
        }
        return null;
    }

    public void deleteEmployee(int id){

    }
}
