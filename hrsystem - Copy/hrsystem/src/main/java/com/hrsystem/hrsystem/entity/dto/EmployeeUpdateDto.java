package com.hrsystem.hrsystem.entity.dto;

import lombok.Data;

@Data
public class EmployeeUpdateDto {
    private Double grossSalary;

    public EmployeeUpdateDto(Double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public EmployeeUpdateDto() {

    }

    public Double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(Double grossSalary) {
        this.grossSalary = grossSalary;
    }
}