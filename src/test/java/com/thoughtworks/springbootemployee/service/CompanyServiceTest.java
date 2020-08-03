package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.IllegalUpdateCompanyException;
import com.thoughtworks.springbootemployee.exception.NoSuchCompanyException;
import com.thoughtworks.springbootemployee.constant.ExceptionMessage;
import com.thoughtworks.springbootemployee.dto.RequestCompany;
import com.thoughtworks.springbootemployee.mapper.DTOMapper;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CompanyServiceTest {

    private static final int COMPANY_ID = 1;
    @InjectMocks
    CompanyService companyService;

    @Mock
    CompanyRepository companyRepository;
    @Mock
    private DTOMapper dtoMapper;

    @Test
    void should_return_companies_when_getCompanies_given_() {
        //given
        List<Company> mockedCompanies = getMockCompanies();
        List<RequestCompany> Rcompanies = singletonList(new RequestCompany(1, "Mm", 10, emptyList()));
        when(companyRepository.findAll()).thenReturn(mockedCompanies);
        when(dtoMapper.toResponseCompanies(mockedCompanies)).thenReturn(Rcompanies);

        //when
        List<RequestCompany> companies = companyService.getCompanies();

        //then
        assertEquals(1, companies.size());

    }

    @Test
    void should_return_companies_when_getCompanies_given_page_and_pageSize() {
        //given
        int page = 1;
        int pageSize = 3;
        Page<Company> companies = new PageImpl<Company>(emptyList());
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
    void should_return_Employees_when_getEmployees_given_company_id() {
        //given
        DTOMapper dtoMapper = new DTOMapper();
        when(companyRepository.findById(eq(COMPANY_ID))).thenReturn(Optional.of(getMockCompany()));

        //when
        List<Employee> employees = companyService.getEmployees(COMPANY_ID);
        //then
        assertEquals(0, employees.size());
    }

    @Test
    void should_return_company_when_create_company_given_company() {
        //given
        Company mockedCompany = getMockCompany();
        RequestCompany requestCompany = new RequestCompany(1,"Mm",10,emptyList());
        when(companyRepository.save(mockedCompany)).thenReturn(mockedCompany);
        when(dtoMapper.toCompany(requestCompany)).thenReturn(mockedCompany);
        when(dtoMapper.toResponseCompany(mockedCompany)).thenReturn(requestCompany);

        //when
        RequestCompany company = companyService.createCompany(requestCompany);

        //then
        assertEquals(requestCompany.getId(),company.getId());
        assertEquals(requestCompany.getCompanyName(),company.getCompanyName());
        assertEquals(requestCompany.getEmployeeNumber(),company.getEmployeeNumber());
        assertEquals(requestCompany.getEmployees(),company.getEmployees());
    }


    @Test
    void should_return_company_when_updateCompany_given_exists_company() throws Exception {
        //given
        when(companyRepository.save(any())).thenReturn(getMockCompany());
        when(companyRepository.findById(COMPANY_ID)).thenReturn(Optional.of(getMockCompany()));

        //when
        RequestCompany requestCompany = new RequestCompany();
        BeanUtils.copyProperties(getMockCompany(),requestCompany);
        RequestCompany companyUpdated = companyService.updateCompany(COMPANY_ID, requestCompany);
        //then
        verify(companyRepository).findById(COMPANY_ID);
        verify(companyRepository).save(isA(Company.class));
    }

    @Test
    void should_throw_ILLEGALECXEPTION_when_updateCompany_given_() throws Exception {
        //given

        //when
        RequestCompany requestCompany = new RequestCompany();
        BeanUtils.copyProperties(getMockCompany(),requestCompany);
        Throwable exception = assertThrows(IllegalUpdateCompanyException.class,
                () -> companyService.updateCompany(2, requestCompany));

        //then
        assertEquals(ExceptionMessage.ILLEGAL_UPDATE_COMPANY.getErrorMsg(), exception.getMessage());
    }

    @Test
    void should_throw_NOSUCHDATAEXCEPTION_when_updateCompany_given_() {
        //given
        when(companyRepository.findById(eq(COMPANY_ID))).thenReturn(Optional.empty());
        //when
        RequestCompany requestCompany = new RequestCompany();
        BeanUtils.copyProperties(getMockCompany(),requestCompany);
        Throwable exception = assertThrows(NoSuchCompanyException.class, () -> companyService.updateCompany(COMPANY_ID, requestCompany));
        //then
        assertEquals(ExceptionMessage.NO_SUCH_COMPANY.getErrorMsg(), exception.getMessage());
    }

    @Test
    void should_void_when_deleteCompany_given_company_id() {
        //given
        RequestCompany requestCompany = new RequestCompany();
        BeanUtils.copyProperties(getMockCompany(),requestCompany);
        when(companyRepository.findById(eq(COMPANY_ID))).thenReturn(Optional.of(getMockCompany()));
        //when
        companyService.deleteCompany(COMPANY_ID);
        //then
        verify(companyRepository).deleteById(eq(COMPANY_ID));
    }

    @Test
    void should_throw_NoSuchDataException_when_deleteCompany_given_company_no_exists() {
        //given
        when(companyRepository.findById(eq(COMPANY_ID))).thenReturn(Optional.empty());
        //when
        Throwable exception = assertThrows(NoSuchCompanyException.class, () -> companyService.deleteCompany(COMPANY_ID));
        //then
        assertEquals(ExceptionMessage.NO_SUCH_COMPANY.getErrorMsg(), exception.getMessage());

    }

    private List<Company> getMockCompanies() {
        List<Company> companies = new ArrayList<>();
        companies.add(new Company(1, "Mm", 10, emptyList()));
        return companies;
    }

    private Company getMockCompany() {
        return new Company(1, "Mm", 10, emptyList());
    }
}
