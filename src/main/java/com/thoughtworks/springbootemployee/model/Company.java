package com.thoughtworks.springbootemployee.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "companyName")
    private String companyName;

    @Column(name = "employNumber")
    private int employeeNumber;

    @OneToMany(cascade = {CascadeType.ALL},mappedBy = "companyId")
    private List<Employee> employees;

}
