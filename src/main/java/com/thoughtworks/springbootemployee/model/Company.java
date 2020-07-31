package com.thoughtworks.springbootemployee.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "company")
public class Company {

    @Id
    private int id;

    @Column(name = "companyName")
    private String companyName;

    @Column(name = "employNumber")
    private int employeeNumber;

    @OneToMany(cascade = {CascadeType.ALL},mappedBy = "companyId")
    private List<Employee> employees;

    public Company(int id, String companyName, int employeeNumber, List<Employee> employees) {
        this.id = id;
        this.companyName = companyName;
        this.employeeNumber = employeeNumber;
        this.employees = employees;
    }

    public Company() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
