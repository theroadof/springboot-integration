package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.utils.PageUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static final String ADD_EMPLOYEE_SUCCESS = "add employee success";
    private static final String UPDATE_EMPLOYEE_SUCCESS = "update employee success";
    private static final String DELETE_EMPLOYEE_SUCCESS = "delete employee success";

    @GetMapping
    public List<Employee> getEmployees(@RequestParam(value = "page", required = false) Integer page,
                                       @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                       @RequestParam(value = "gender", required = false) String gender) {
        if (Objects.nonNull(gender)) {
            getEmployeeList().stream().filter(employee -> employee.getGender().equals(gender)).findFirst().orElse(null);
        }
        if (Objects.nonNull(page) && Objects.nonNull(pageSize)) {
            return (List<Employee>) PageUtils.getPage(getEmployeeList(), page, pageSize);
        }
        return getEmployeeList();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable("id") int id) {
        List<Employee> employees = getEmployeeList();
        return employees.stream().filter(employee -> employee.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public String addEmployee(Employee employee) {
        return ADD_EMPLOYEE_SUCCESS;
    }

    @PutMapping("/{id}")
    public String updateEmployee(@PathVariable("id") String id) {
        return UPDATE_EMPLOYEE_SUCCESS;
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable("id") String id) {
        return DELETE_EMPLOYEE_SUCCESS;
    }

    private List<Employee> getEmployeeList() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "q", 18, "male", new BigDecimal(3000)));
        employees.add(new Employee(2, "w", 18, "male", new BigDecimal(3000)));
        return employees;
    }
}
