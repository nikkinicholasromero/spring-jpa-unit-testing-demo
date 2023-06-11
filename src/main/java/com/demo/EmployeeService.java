package com.demo;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeTransformer employeeTransformer;

    public EmployeeService(
            EmployeeRepository employeeRepository,
            EmployeeTransformer employeeTransformer) {
        this.employeeRepository = employeeRepository;
        this.employeeTransformer = employeeTransformer;
    }

    public List<EmployeeDto> findAll() {
        return IterableUtils.toList(employeeRepository.findAll())
                .stream()
                .map(employeeTransformer::transform)
                .toList();
    }

    public EmployeeDto findById(String id) {
        return employeeTransformer.transform(employeeRepository.findById(id).orElseThrow());
    }

    public void createEmployee(CreateEmployeeRequest request) {
        Employee employee = new Employee(
                UUID.randomUUID().toString(),
                request.firstName(),
                request.lastName(),
                request.numberOfDependents(),
                request.height(),
                request.weight(),
                request.hiredDate(),
                request.startTime(),
                request.isRegular());

        employeeRepository.save(employee);
    }

    public void updateEmployee(String id, UpdateEmployeeRequest request) {
        Employee employee = employeeRepository.findById(id).orElseThrow();

        employee.updateName(request.firstName(), request.lastName());
        employee.updateNumberOfDependents(request.numberOfDependents());
        employee.updateBodyInformation(request.height(), request.weight());
        employee.updateEmploymentInformation(request.hiredDate(), request.startTime(), request.isRegular());

        employeeRepository.save(employee);
    }

    public void deleteEmployee(String id) {
        employeeRepository.deleteById(id);
    }
}
