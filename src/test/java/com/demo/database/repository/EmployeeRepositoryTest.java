package com.demo.database.repository;

import com.demo.database.entity.Employee;
import org.apache.commons.collections4.IterableUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployeeRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void findAll() {
        List<Employee> employees = IterableUtils.toList(employeeRepository.findAll());
        assertThat(employees).isEmpty();

        String id = UUID.randomUUID().toString();
        LocalDate hiredDate = LocalDate.now();
        LocalTime startTime = LocalTime.now();
        Employee employee = new Employee(
                id,
                "Nikki Nicholas",
                "Romero",
                BigInteger.valueOf(3L),
                BigDecimal.valueOf(177.2),
                BigDecimal.valueOf(76.3),
                hiredDate,
                startTime,
                false);

        entityManager.persist(employee);

        employees = IterableUtils.toList(employeeRepository.findAll());
        assertThat(employees).isNotEmpty();
        assertThat(employees.size()).isEqualTo(1L);
        assertThat(employees.get(0)).isEqualTo(employee);
        assertThat(employees.get(0).id()).isEqualTo(id);
        assertThat(employees.get(0).firstName()).isEqualTo("Nikki Nicholas");
        assertThat(employees.get(0).lastName()).isEqualTo("Romero");
        assertThat(employees.get(0).numberOfDependents()).isEqualTo(BigInteger.valueOf(3L));
        assertThat(employees.get(0).height()).isEqualTo(BigDecimal.valueOf(177.2));
        assertThat(employees.get(0).weight()).isEqualTo(BigDecimal.valueOf(76.3));
        assertThat(employees.get(0).hiredDate()).isEqualTo(hiredDate);
        assertThat(employees.get(0).startTime()).isEqualTo(startTime);
        assertThat(employees.get(0).isRegular()).isFalse();
    }

    @Test
    public void findById() {
        String id1 = UUID.randomUUID().toString();
        LocalDate hiredDate1 = LocalDate.now();
        LocalTime startTime1 = LocalTime.now();
        Employee employee1 = new Employee(
                id1,
                "Nikki Nicholas",
                "Romero",
                BigInteger.valueOf(3L),
                BigDecimal.valueOf(177.2),
                BigDecimal.valueOf(76.3),
                hiredDate1,
                startTime1,
                false);

        entityManager.persist(employee1);

        String id2 = UUID.randomUUID().toString();
        LocalDate hiredDate2 = LocalDate.now();
        LocalTime startTime2 = LocalTime.now();
        Employee employee2 = new Employee(
                id2,
                "Leslie Anne",
                "Sayin",
                BigInteger.valueOf(2L),
                BigDecimal.valueOf(145.2),
                BigDecimal.valueOf(64.3),
                hiredDate2,
                startTime2,
                true);

        entityManager.persist(employee2);

        Employee fetchedEmployee1 = employeeRepository.findById(employee1.id()).orElseThrow();
        assertThat(fetchedEmployee1).isEqualTo(employee1);
        assertThat(fetchedEmployee1.id()).isEqualTo(id1);
        assertThat(fetchedEmployee1.firstName()).isEqualTo("Nikki Nicholas");
        assertThat(fetchedEmployee1.lastName()).isEqualTo("Romero");
        assertThat(fetchedEmployee1.numberOfDependents()).isEqualTo(BigInteger.valueOf(3L));
        assertThat(fetchedEmployee1.height()).isEqualTo(BigDecimal.valueOf(177.2));
        assertThat(fetchedEmployee1.weight()).isEqualTo(BigDecimal.valueOf(76.3));
        assertThat(fetchedEmployee1.hiredDate()).isEqualTo(hiredDate1);
        assertThat(fetchedEmployee1.startTime()).isEqualTo(startTime1);
        assertThat(fetchedEmployee1.isRegular()).isFalse();

        Employee fetchedEmployee2 = employeeRepository.findById(employee2.id()).orElseThrow();
        assertThat(fetchedEmployee2).isEqualTo(employee2);
        assertThat(fetchedEmployee2.id()).isEqualTo(id2);
        assertThat(fetchedEmployee2.firstName()).isEqualTo("Leslie Anne");
        assertThat(fetchedEmployee2.lastName()).isEqualTo("Sayin");
        assertThat(fetchedEmployee2.numberOfDependents()).isEqualTo(BigInteger.valueOf(2L));
        assertThat(fetchedEmployee2.height()).isEqualTo(BigDecimal.valueOf(145.2));
        assertThat(fetchedEmployee2.weight()).isEqualTo(BigDecimal.valueOf(64.3));
        assertThat(fetchedEmployee2.hiredDate()).isEqualTo(hiredDate2);
        assertThat(fetchedEmployee2.startTime()).isEqualTo(startTime2);
        assertThat(fetchedEmployee2.isRegular()).isTrue();

        assertThat(employeeRepository.findById(employee1.id() + employee2.id())).isEmpty();
    }
}
