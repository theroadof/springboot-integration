package com.thoughtworks.springbootemployee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    private int id;

    private String companyName;

    private int employeeNumber;

    private List<Employee> employees;
    
}
