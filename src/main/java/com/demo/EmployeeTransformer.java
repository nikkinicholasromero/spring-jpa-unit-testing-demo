package com.demo;

import org.springframework.stereotype.Component;

@Component
public class EmployeeTransformer {
    public EmployeeDto transform(Employee e) {
        return new EmployeeDto(
                e.id(),
                e.firstName(),
                e.lastName(),
                e.numberOfDependents(),
                e.height(),
                e.weight(),
                e.hiredDate(),
                e.startTime(),
                e.isRegular());
    }
}
