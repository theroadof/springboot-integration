package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.Times;
import org.mockito.verification.Timeout;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeeServiceTest {

    private static final String MALE = "Male";
    private static final int EMPLOYEE_ID = 1;

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
    void should_return_male_employees_when_getEmployeesByGender_given_gender_is_male() {
        //given
        EmployeeService employService = new EmployeeService(new EmployeeRepository());

        //when
        List<Employee> employees = employService.queryEmployeesByGender(MALE);

        //then
        assertNotNull(employees);
    }

    @Test
    void should_return_employees_when_getEmployeesByPage_given_current_page_is_1_and_page_size_2() {
        //given
        EmployeeService employService = new EmployeeService(new EmployeeRepository());

        //when
        List<Employee> employees = employService.queryEmployeesByPage(1, 2);

        //then
        assertEquals(2, employees.size());
    }

    @Test
    void should_return_employee_when_getEmployee_given_employee_id() {
        //given
        EmployeeService employService = new EmployeeService(new EmployeeRepository());
        //when
        Employee employee = employService.queryEmployee(EMPLOYEE_ID);

        //then
        assertNotNull(employee);
    }

    @Test
    void should_return_employee_when_createEmployee_given_employee() {
        //given
        EmployeeService employService = new EmployeeService(new EmployeeRepository());
        //when
        Employee employee = employService.createEmployee(new Employee(11, "tom chen", 18, "Male", new BigDecimal(9999)));
        //then
        assertNotNull(employee);
    }

    @Test
    void should_return_null_when_createEmployee_given_employee_exists() {
        //given
        EmployeeService employService = new EmployeeService(new EmployeeRepository());
        //when
        Employee employee = employService.createEmployee(new Employee(1, "tom chen", 18, "Male", new BigDecimal(9999)));
        //then
        assertNull(employee);
    }

    @Test
    void should_return_employee_when_updateEmployee_given_exists_employee() {
        //given
        EmployeeService employeeService = new EmployeeService(new EmployeeRepository());
        Employee employee = new Employee(1, "xiaoshiyi", 18, "Male", new BigDecimal(5000));

        //when
        Employee employeeUpdated = employeeService.updateEmployee(employee);

        //then
        assertNotNull(employeeUpdated);
    }

    @Test
    void should_return_employee_when_updateEmployee_given_new_employee() {
        //given
        EmployeeService employeeService = new EmployeeService(new EmployeeRepository());
        Employee employee = new Employee(12, "xiaoshiyi", 18, "Male", new BigDecimal(5000));

        //when
        Employee employeeUpdated = employeeService.updateEmployee(employee);

        //then
        assertNull(employeeUpdated);
    }

    @Test
    void should_void_when_deleteEmployee_given_employee_id() {
        //given
        EmployeeRepository mockEmployeeRepository = mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(mockEmployeeRepository);

        //when
        employeeService.deleteEmployee(EMPLOYEE_ID);

        //then
        verify(mockEmployeeRepository,times(1)).deleteEmployee(anyInt());

    }
}
