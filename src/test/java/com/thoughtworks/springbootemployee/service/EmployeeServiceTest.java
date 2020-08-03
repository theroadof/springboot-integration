package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.IllegalUpdateEmployeeException;
import com.thoughtworks.springbootemployee.constant.ExceptionMessage;
import com.thoughtworks.springbootemployee.dto.RequestEmployee;
import com.thoughtworks.springbootemployee.mapper.DTOMapper;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeeServiceTest {

    private static final String MALE = "Male";
    private static final int EMPLOYEE_ID = 1;

    @Mock
    private DTOMapper dtoMapper;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;
    @Autowired
    private CompanyRepository companyRepository;


    @Test
    void should_return_employees_when_getEmployees_given_() {
        //given
        when(employeeRepository.findAll()).thenReturn(asList(new Employee(1,"teddy",22,"male",new BigDecimal(100),1)));

        //when
        List<RequestEmployee> employees = employeeService.queryEmployees();

        //then
        assertNotNull(employees);

    }

    @Test
    void should_return_male_employees_when_getEmployeesByGender_given_gender_is_male() {
        //given
        when(employeeRepository.findAllByGender(any())).thenReturn(emptyList());
        //when
        List<RequestEmployee> employees = employeeService.queryEmployeesByGender(MALE);

        //then
        assertNotNull(employees);
    }

    @Test
    void should_return_employees_when_getEmployeesByPage_given_current_page_is_1_and_page_size_2() {
        //given
        Page<Employee> employeeswithPage = new PageImpl<Employee>(asList(new Employee(1, "xx", 18, "Male", new BigDecimal(2), 1),
                new Employee(2, "xx", 19, "Male", new BigDecimal(2), 1)));
        when(employeeRepository.findAll(isA(PageRequest.class))).thenReturn(employeeswithPage);

        //when
        Page<RequestEmployee> employees = employeeService.queryEmployeesByPage(1, 2);

        //then
        assertEquals(2, employees.getSize());
    }

    @Test
    void should_return_employee_when_getEmployee_given_employee_id() {
        //given
        when(employeeRepository.findById(any())).thenReturn(Optional.of(new Employee(1, "xx", 18, "Male", new BigDecimal(2), 1)));

        //when
        RequestEmployee employee = employeeService.queryEmployee(EMPLOYEE_ID);

        //then
        assertNotNull(employee);
    }

    @Test
    void should_return_employee_when_createEmployee_given_employee() {
        //given
        RequestEmployee requestEmployee = new RequestEmployee();
        Employee employee = new Employee(11, "tom chen", 18, "Male", new BigDecimal(9999), 1);
        BeanUtils.copyProperties(employee,requestEmployee);
        companyRepository.save(new Company(1,"oocl",0,emptyList()));
        when(dtoMapper.toEmployee(requestEmployee)).thenReturn(employee);
        when(employeeRepository.save(employee)).thenReturn(employee);

        //when
        RequestEmployee savedEmployee = employeeService.createEmployee(requestEmployee);

        //then
        assertEquals(savedEmployee.getId(),employee.getId());
        assertEquals(savedEmployee.getName(),employee.getName());
    }

    @Test
    void should_return_null_when_createEmployee_given_employee_exists() {
        //given
        RequestEmployee requestEmployee = new RequestEmployee();
        Employee employee = new Employee(1, "tom chen", 18, "Male", new BigDecimal(9999), 1);
        BeanUtils.copyProperties(employee,requestEmployee);
        companyRepository.save(new Company(1,"oocl",0,emptyList()));
        when(dtoMapper.toEmployee(requestEmployee)).thenReturn(employee);
        when(employeeRepository.save(employee)).thenReturn(null);

        //when
        RequestEmployee savedEmployee = employeeService.createEmployee(requestEmployee);
        //then
        assertNull(savedEmployee);
    }

    @Test
    void should_return_employee_when_updateEmployee_given_exists_employee() throws Exception {
        //given
        Employee employee = new Employee(1, "xiaoshiyi", 18, "Male", new BigDecimal(5000), 1);
        when(employeeRepository.save(any())).thenReturn(employee);
        when(employeeRepository.findById(EMPLOYEE_ID)).thenReturn(Optional.of(employee));
        RequestEmployee requestEmployee = new RequestEmployee();
        BeanUtils.copyProperties(employee,requestEmployee);

        //when
        RequestEmployee employeeUpdated = employeeService.updateEmployee(EMPLOYEE_ID, requestEmployee);

        //then
        assertEquals(employee.getId(),employeeUpdated.getId());
    }

    @Test
    void should_return_employee_when_updateEmployee_given_new_employee() throws Exception {
        //given
        RequestEmployee employee = new RequestEmployee(2, "xiaoshiyi", 18, "Male", new BigDecimal(5000), 1);

        //when
        Throwable exception = assertThrows(IllegalUpdateEmployeeException.class, () -> employeeService.updateEmployee(EMPLOYEE_ID, employee));

        //then
        assertEquals(ExceptionMessage.ILLEGAL_UPDATE_EMPLOYEE.getErrorMsg(), exception.getMessage());
    }

    @Test
    void should_void_when_deleteEmployee_given_employee_id() {
        //given
        Employee employee = new Employee(1, "xiaoshiyi", 18, "Male", new BigDecimal(5000), 1);
        when(employeeRepository.findById(eq(EMPLOYEE_ID))).thenReturn(
                Optional.of(employee)
        );

        //when
        employeeService.deleteEmployee(EMPLOYEE_ID);

        //then
        verify(employeeRepository, times(1)).deleteById(eq(EMPLOYEE_ID));
    }
}
