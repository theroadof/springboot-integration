package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Exception.NoSuchDataException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CompanyServiceTest {

    private static final int COMPANY_ID = 1;
    @Autowired
    CompanyService companyService;

    @MockBean
    EmployeeRepository employeeRepository;

    @MockBean
    CompanyRepository companyRepository;

    @Test
    void should_return_companies_when_getCompanies_given_() {
        //given
        when(companyRepository.findAll()).thenReturn(getMockCompanies());

        //when
        List<Company> companies = companyService.getCompanies();

        //then
        assertEquals(10, companies.size());

    }

    @Test
    void should_return_companies_when_getCompanies_given_page_and_pageSize() {
        //given
        int page = 1;
        int pageSize = 3;
        Page<Company> companies = new PageImpl<Company>(asList());
        when(companyRepository.findAll(isA(PageRequest.class))).thenReturn(companies);
        //when
        companyService.getCompaniesPage(page, pageSize);
        //then
        verify(companyRepository).findAll(isA(PageRequest.class));
    }

    @Test
    void should_return_company_when_getCompany_given_company_id() {
        //given

        when(companyRepository.findById(eq(COMPANY_ID))).thenReturn(Optional.of(new Company()));
        //when
        companyService.getCompany(COMPANY_ID);

        //then
        verify(companyRepository).findById(eq(COMPANY_ID));
    }

    @Test
    void should_return_Employees_when_getEmployees_given_company_id() throws NoSuchDataException {
        //given
        when(companyRepository.findById(eq(COMPANY_ID))).thenReturn(Optional.of(getMockCompany()));

        //when
        List<Employee> employees = companyService.getEmployees(COMPANY_ID);
        //then
        assertEquals(10, employees.size());
    }

    @Test
    void should_return_employee_when_create_company_given_company() {
        //given
        when(companyRepository.save(any())).thenReturn(any());

        //when
        Company company = companyService.createCompany(getMockCompany());

        //then
        verify(companyRepository).save(any());
    }

    private List<Company> getMockCompanies() {
        List<Employee> employees = new ArrayList<>();
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
        List<Company> companies = new ArrayList<>();
        companies.add(new Company(1, "Mm", 10, employees));
        companies.add(new Company(2, "Aa", 10, employees));
        companies.add(new Company(3, "Bb", 10, employees));
        companies.add(new Company(4, "Cc", 10, employees));
        companies.add(new Company(5, "Dd", 10, employees));
        companies.add(new Company(6, "Ff", 10, employees));
        companies.add(new Company(7, "Gg", 10, employees));
        companies.add(new Company(8, "Ll", 10, employees));
        companies.add(new Company(9, "Pp", 10, employees));
        companies.add(new Company(10, "Uu", 10, employees));
        return companies;
    }

    private Company getMockCompany() {
        List<Employee> employees = new ArrayList<>();
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
        return new Company(1, "Mm", 10, employees);
    }
}
