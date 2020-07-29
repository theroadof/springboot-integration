package com.thoughtworks.springbootemployee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "companyName")
    private String companyName;

    @Column(name = "employNumber")
    private int employeeNumber;

    @OneToMany
    private List<Employee> employees;

}
