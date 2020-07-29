package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class EmployeeServiceTest {

    private static final String MALE = "Male";

    @Test
    void should_return_employees_when_getEmployees_given_() {
        //given
        EmployeeService employService = new EmployeeService(new EmployeeRepository());

        //when
        List<Employee> employees = employService.queryEmployees();

        //then
        assertNotNull(employees);

    }

    @Test
    void should_return_male_employees_when_getEmployees_given_gender_is_male() {
        //given
        EmployeeService employService = new EmployeeService(new EmployeeRepository());

        //when
        List<Employee> employees = employService.queryEmployeesByGender(MALE);

        //then
        assertNotNull(employees);
    }
}
