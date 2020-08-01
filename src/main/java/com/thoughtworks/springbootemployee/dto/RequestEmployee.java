package com.thoughtworks.springbootemployee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestEmployee {
    private int id;

    private String name;

    private int age;

    private String gender;

    private BigDecimal salary;

    private int companyId;
}
