package com.thoughtworks.springbootemployee.integrationTest;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Before
    void clearAllBefore() {
        employeeRepository.deleteAll();
        companyRepository.deleteAll();
    }

    @AfterEach
    private void clearAll() {
        employeeRepository.deleteAll();
        companyRepository.deleteAll();
        System.out.println("ceshi123");
        System.out.println(companyRepository.findAll());
        System.out.println(employeeRepository.findAll());
    }

    @Test
    void should_return_employees_in_page_when_getEmployeeInPage_given_page_and_pageSize() throws Exception {
        //given
        Company oocl = companyRepository.save(new Company(1, "oocl", 1, emptyList()));
        Employee xiaoYi = employeeRepository.save(new Employee(1, "XIAOYI", 18, "Male", new BigDecimal(3000), oocl.getId()));
        Employee xiaoWang = employeeRepository.save(new Employee(2, "xiaowang", 18, "Male", new BigDecimal(3000), oocl.getId()));
        //when
        mockMvc.perform(get("/employees").contentType(MediaType.APPLICATION_JSON)
                .param("page", "1").param("pageSize", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id").value(xiaoYi.getId()));
    }

    @Test
    void should_return_male_employees_when_getEmployeesByGender_given_employees() throws Exception {
        //given
        Company oocl = companyRepository.save(new Company(1, "oocl", 1, emptyList()));
        Employee devin = employeeRepository.save(new Employee(1, "Devin", 22, "male", new BigDecimal(9999), oocl.getId()));
        Employee xiaoHong = employeeRepository.save(new Employee(2, "xiaohong", 22, "female", new BigDecimal(9999), oocl.getId()));

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
        Company oocl = companyRepository.save(new Company(1, "oocl", 1, emptyList()));
        Employee devin = employeeRepository.save(new Employee(1, "Devin", 22, "male", new BigDecimal(9999), oocl.getId()));
        Employee xiaoHong = employeeRepository.save(new Employee(2, "xiaohong", 22, "female", new BigDecimal(9999), oocl.getId()));

        //when
        mockMvc.perform(get("/employees").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void should_return_employee_by_id_when_getEmployee_given_id() throws Exception {
        //given
        Company oocl = companyRepository.save(new Company(1, "oocl", 1, emptyList()));

        Employee devin = employeeRepository.save(new Employee(1, "Devin", 22, "male", new BigDecimal(9999), oocl.getId()));
        Employee xiaoHong = employeeRepository.save(new Employee(2, "xiaohong", 22, "female", new BigDecimal(9999), oocl.getId()));
        //when
        mockMvc.perform(get("/employees/" + devin.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value(devin.getName()))
                .andExpect(jsonPath("$.age").value(devin.getAge()))
                .andExpect(jsonPath("$.gender").value(devin.getGender()))
                .andExpect(jsonPath("$.salary").isNumber())
                .andExpect(jsonPath("$.companyId").value(devin.getCompanyId()));
    }

    @Test
    void should_return_employee_when_addEmployee_given_employee() throws Exception {
        //given
        String employeeString = "{\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Devin\",\n" +
                "    \"age\": 18,\n" +
                "    \"gender\": \"male\",\n" +
                "    \"salary\": 3000,\n" +
                "    \"companyId\": 1\n" +
                "}";
        Company oocl = companyRepository.save(new Company(1, "oocl", 1, emptyList()));

        //when
        mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON)
                .content(employeeString))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Devin"))
                .andExpect(jsonPath("$.age").value(18))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").isNumber())
                .andExpect(jsonPath("$.companyId").value(1));
    }

    @Test
    void should_return_employee_when_update_employee_given_id_and_employee() throws Exception {
        //given
        Company oocl = companyRepository.save(new Company(1, "oocl", 1, emptyList()));
        Employee save = employeeRepository.save(new Employee(1, "Devin", 22, "male", new BigDecimal(9999), oocl.getId()));
        String employeeString = "{\n" +
                "    \"id\": " + save.getId() + ",\n" +
                "    \"name\": \"Devin\",\n" +
                "    \"age\": 30,\n" +
                "    \"gender\": \"male\",\n" +
                "    \"salary\": 3000,\n" +
                "    \"companyId\": 1\n" +
                "}";
        //when
        mockMvc.perform(put("/employees/" + save.getId()).contentType(MediaType.APPLICATION_JSON)
                .content(employeeString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Devin"))
                .andExpect(jsonPath("$.age").value(30))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").isNumber())
                .andExpect(jsonPath("$.companyId").value(oocl.getId()));
    }

    @Test
    void should_return_status_is_ok_when_deleteEmployee_given_id() throws Exception {
        //given
        Company oocl = companyRepository.save(new Company(1, "oocl", 1, emptyList()));
        Employee save = employeeRepository.save(new Employee(1, "Devin", 22, "male", new BigDecimal(9999), oocl.getId()));

        //when
        mockMvc.perform(delete("/employees/" + save.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
