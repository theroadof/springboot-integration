package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CompanyServiceTest {

    @Autowired
    CompanyService companyService;

    @Test
    void should_return_companies_when_getCompanies_given_() {
        //given

        //when
        List<Company> companies = companyService.getCompanies();

        //then
        assertNotNull(companies);

    }
}
