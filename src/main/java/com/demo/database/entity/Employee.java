package com.demo.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Employee {
    @Id
    private String id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private BigInteger numberOfDependents;

    @Column
    private BigDecimal height;

    @Column
    private BigDecimal weight;

    @Column
    private LocalDate hiredDate;

    @Column
    private LocalTime startTime;

    @Column
    private boolean isRegular;

    protected Employee() {
        // DO NOT USE
    }

    public Employee(
            String id,
            String firstName,
            String lastName,
            BigInteger numberOfDependents,
            BigDecimal height,
            BigDecimal weight,
            LocalDate hiredDate,
            LocalTime startTime,
            boolean isRegular) {
        this.id = id;
        this.firstName = StringUtils.trimToEmpty(firstName);
        this.lastName = StringUtils.trimToEmpty(lastName);
        this.numberOfDependents = numberOfDependents;
        this.height = height;
        this.weight = weight;
        this.hiredDate = hiredDate;
        this.startTime = startTime;
        this.isRegular = isRegular;
    }

    public String id() {
        return id;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public BigInteger numberOfDependents() {
        return numberOfDependents;
    }

    public BigDecimal height() {
        return height;
    }

    public BigDecimal weight() {
        return weight;
    }

    public LocalDate hiredDate() {
        return hiredDate;
    }

    public LocalTime startTime() {
        return startTime;
    }

    public boolean isRegular() {
        return isRegular;
    }

    public void updateName(String firstName, String lastName) {
        this.firstName = StringUtils.trimToEmpty(firstName);
        this.lastName = StringUtils.trimToEmpty(lastName);
    }

    public void updateNumberOfDependents(BigInteger numberOfDependents) {
        this.numberOfDependents = numberOfDependents;
    }

    public void updateBodyInformation(BigDecimal height, BigDecimal weight) {
        this.height = height;
        this.weight = weight;
    }

    public void updateEmploymentInformation(LocalDate hiredDate, LocalTime startTime, boolean isRegular) {
        this.hiredDate = hiredDate;
        this.startTime = startTime;
        this.isRegular = isRegular;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        return new EqualsBuilder().append(id, employee.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("numberOfDependents", numberOfDependents)
                .append("height", height)
                .append("weight", weight)
                .append("hiredDate", hiredDate)
                .append("startTime", startTime)
                .append("isRegular", isRegular)
                .toString();
    }
}
