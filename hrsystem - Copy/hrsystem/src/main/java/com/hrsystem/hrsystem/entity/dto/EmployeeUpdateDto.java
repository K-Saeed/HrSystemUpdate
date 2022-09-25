package com.hrsystem.hrsystem.entity.dto;

import lombok.Data;

@Data
public class EmployeeUpdateDto {
    private Integer grossSalary;

    public EmployeeUpdateDto(Integer grossSalary) {
        this.grossSalary = grossSalary;
    }

    public EmployeeUpdateDto() {

    }

    public Integer getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(Integer grossSalary) {
        this.grossSalary = grossSalary;
    }
}