package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CompanyRepository {
    private static List<Company> companies;

    private static List<Employee> employees;

    static {
        employees = new ArrayList<>();
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
        companies = new ArrayList<>();
        companies.add(new Company(1,"Mm",10,employees));
        companies.add(new Company(2,"Aa",10,employees));
        companies.add(new Company(3,"Bb",10,employees));
        companies.add(new Company(4,"Cc",10,employees));
        companies.add(new Company(5,"Dd",10,employees));
        companies.add(new Company(6,"Ff",10,employees));
        companies.add(new Company(7,"Gg",10,employees));
        companies.add(new Company(8,"Ll",10,employees));
        companies.add(new Company(9,"Pp",10,employees));
        companies.add(new Company(10,"Uu",10,employees));
    }


    public List<Company> getCompanies() {
        return companies;
    }
}
