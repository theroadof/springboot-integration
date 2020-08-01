package com.thoughtworks.springbootemployee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "employee")
public class Employee {

    @Id
    private int id;

    private String name;

    private int age;

    private String gender;

    private BigDecimal salary;

    private int companyId;

    public Employee(int id, String name, int age, String gender, BigDecimal salary, int companyId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.companyId = companyId;
    }

    public Employee() {
    }
}
