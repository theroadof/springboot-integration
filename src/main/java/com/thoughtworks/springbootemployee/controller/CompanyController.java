package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.RequestCompany;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping(params = {"page", "pageSize"})
    public Page<RequestCompany> getCompaniesInPage(@RequestParam(value = "page") Integer page,
                                            @RequestParam(value = "pageSize") Integer pageSize) {
        return companyService.getCompaniesPage(page, pageSize);
    }

    @GetMapping
    public List<RequestCompany> getCompanies() {
        return companyService.getCompanies();
    }

    @GetMapping("/{id}")
    public RequestCompany getCompany(@PathVariable int id) {
        return companyService.getCompany(id);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeeFromCompany(@PathVariable int id) {
        return companyService.getCompany(id).getEmployees();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RequestCompany addCompany(@RequestBody RequestCompany requestCompany) {
        return companyService.createCompany(requestCompany);
    }

    @PutMapping("/{id}")
    public RequestCompany updateCompany(@PathVariable("id") int id, @RequestBody RequestCompany requestCompany) {
        return companyService.updateCompany(id, requestCompany);
    }

    @DeleteMapping("{id}")
    public void deleteCompany(@PathVariable("id") int id) {
        companyService.deleteCompany(id);
    }

}
