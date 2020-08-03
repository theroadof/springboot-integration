package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.RequestCompany;
import com.thoughtworks.springbootemployee.dto.ResponseCompany;
import com.thoughtworks.springbootemployee.model.Company;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Component
public class CompanyMapper {
    public Company toCompany(RequestCompany requestCompany){
        if(isNull(requestCompany)) {return null;}
        Company company = new Company();
        BeanUtils.copyProperties(requestCompany,company);
        return company;
    }

    public ResponseCompany toResponseCompany(Company company){
        if(isNull(company)) {return null;}
        ResponseCompany responseCompany = new ResponseCompany();
        BeanUtils.copyProperties(company,responseCompany);
        return responseCompany;
    }

    public List<ResponseCompany> toResponseCompanies(List<Company> companies){
        if(isNull(companies)) {return null;}
        List<ResponseCompany> responseCompanies = new ArrayList<>();
        companies.forEach(company -> {
            responseCompanies.add(toResponseCompany(company));
        });
        return responseCompanies;
    }

    public Page<ResponseCompany> toResponseCompanyPage(Page<Company> companies){
        if (isNull(companies)) {return null;}
        List<ResponseCompany> responseCompanies = new ArrayList<>();
        companies.forEach(company -> {
            responseCompanies.add(toResponseCompany(company));
        });
        return new PageImpl<ResponseCompany>(responseCompanies);
    }
}
