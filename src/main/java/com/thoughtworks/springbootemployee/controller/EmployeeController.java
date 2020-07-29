package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    /*@Autowired
    private EmployeeService employeeService;

    private static final String ADD_EMPLOYEE_SUCCESS = "add employee success";
    private static final String UPDATE_EMPLOYEE_SUCCESS = "update employee success";
    private static final String DELETE_EMPLOYEE_SUCCESS = "delete employee success";

    @GetMapping
    public List<Employee> getEmployees(@RequestParam(value = "page", required = false) Integer page,
                                       @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                       @RequestParam(value = "gender", required = false) String gender) {
        if (Objects.nonNull(gender)) {
            return employeeService.queryEmployeesByGender(gender);
        }
        if (Objects.nonNull(page) && Objects.nonNull(pageSize)) {
            return employeeService.queryEmployeesByPage(page, pageSize);
        }
        return employeeService.queryEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable("id") int id) {
        return employeeService.queryEmployee(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {
        employeeService.deleteEmployee(id);
        return DELETE_EMPLOYEE_SUCCESS;
    }
*/
}
