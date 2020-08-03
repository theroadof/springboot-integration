package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.RequestEmployee;
import com.thoughtworks.springbootemployee.dto.ResponseEmployee;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(params = {"page", "pageSize"})
    public Page<ResponseEmployee> getEmployeePage(@RequestParam(value = "page") Integer page,
                                                  @RequestParam(value = "pageSize") Integer pageSize) {
        return employeeService.queryEmployeesByPage(page, pageSize);
    }

    @GetMapping(params = {"gender"})
    public List<ResponseEmployee> getEmployeesByGender(@RequestParam(value = "gender") String gender) {
        return employeeService.queryEmployeesByGender(gender);
    }

    @GetMapping
    public List<ResponseEmployee> getEmployees() {
        return employeeService.queryEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEmployee getEmployee(@PathVariable("id") int id) {
        return employeeService.queryEmployee(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEmployee addEmployee(@RequestBody RequestEmployee requestEmployee) {
        return employeeService.createEmployee(requestEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEmployee updateEmployee(@PathVariable("id") int id, @RequestBody RequestEmployee requestEmployee) {
        return employeeService.updateEmployee(id, requestEmployee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") int id) {
        employeeService.deleteEmployee(id);
    }

}
