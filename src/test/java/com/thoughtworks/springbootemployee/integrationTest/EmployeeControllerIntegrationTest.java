package com.thoughtworks.springbootemployee.integrationTest;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerIntegrationTest {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    MockMvc mockMvc;

    @AfterEach
    private void clearAll() {
        employeeRepository.deleteAll();
        companyRepository.deleteAll();
    }


    @Test
    void should_return_employees_in_page_when_getEmployeeInPage_given_page_and_pageSize() throws Exception {
        //given
        companyRepository.save(new Company(1, "oocl", 1, emptyList()));
        employeeRepository.save(new Employee(1, "XIAOYI", 18, "Male", new BigDecimal(3000), 1));
        employeeRepository.save(new Employee(2, "xiaowang", 18, "Male", new BigDecimal(3000), 1));
        //when
        mockMvc.perform(get("/employees").contentType(MediaType.APPLICATION_JSON)
                .param("page", "1").param("pageSize", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id").value(1));
    }

    @Test
    void should_return_male_employees_when_getEmployeesByGender_given_employees() throws Exception {
        //given
        companyRepository.save(new Company(1, "oocl", 1, emptyList()));
        employeeRepository.save(new Employee(1, "Devin", 22, "male", new BigDecimal(9999), 1));
        employeeRepository.save(new Employee(2, "xiaohong", 22, "female", new BigDecimal(9999), 1));

        String gender = "male";

        //when
        mockMvc.perform(get("/employees").contentType(MediaType.APPLICATION_JSON)
                .param("gender", gender))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].gender").value("male"));
    }

    @Test
    void should_return_employees_when_getEmployees_given_employees() throws Exception {
        //given
        companyRepository.save(new Company(1, "oocl", 1, emptyList()));
        employeeRepository.save(new Employee(1, "Devin", 22, "male", new BigDecimal(9999), 1));
        employeeRepository.save(new Employee(2, "xiaohong", 22, "female", new BigDecimal(9999), 1));

        //when
        mockMvc.perform(get("/employees").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
