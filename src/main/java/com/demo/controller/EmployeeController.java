package com.demo.controller;

import com.demo.dto.CreateEmployeeRequest;
import com.demo.dto.EmployeeDto;
import com.demo.dto.UpdateEmployeeRequest;
import com.demo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({ "/employee", "/employees" })
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDto> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public EmployeeDto findById(@PathVariable String id) {
        return employeeService.findById(id);
    }

    @PostMapping
    public void createEmployee(@RequestBody CreateEmployeeRequest request) {
        employeeService.createEmployee(request);
    }

    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable String id, @RequestBody UpdateEmployeeRequest request) {
        employeeService.updateEmployee(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
    }
}
