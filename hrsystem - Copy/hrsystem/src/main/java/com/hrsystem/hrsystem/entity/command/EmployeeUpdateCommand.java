package com.hrsystem.hrsystem.entity.command;

import lombok.Data;

@Data
public class EmployeeUpdateCommand {
    private Double grossSalary;

    public EmployeeUpdateCommand(Double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public EmployeeUpdateCommand() {

    }

    public Double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(Double grossSalary) {
        this.grossSalary = grossSalary;
    }
}
