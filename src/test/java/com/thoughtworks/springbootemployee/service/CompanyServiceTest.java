package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CompanyServiceTest {

    private static final int COMPANY_ID = 1;
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
/*
    @Test
    void should_return_companies_when_getCompanies_given_page_and_pageSize() {
        //given
        int page = 1;
        int pageSize = 3;
        //when
       List<Company> companies = companyService.getCompaniesPage(page,pageSize);
        //then
        assertEquals(3,companies.size());

    }

    @Test
    void should_return_company_when_getCompany_given_company_id() {
        //given

        //when
        Company company = companyService.getCompany(COMPANY_ID);

        //then
        assertNotNull(company);
    }

    @Test
    void should_return__when__given_() {
        //given

        //when
        List<>
        //then

    }*/
}
