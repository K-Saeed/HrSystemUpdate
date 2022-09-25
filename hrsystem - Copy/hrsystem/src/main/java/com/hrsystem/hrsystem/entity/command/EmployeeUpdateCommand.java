package com.hrsystem.hrsystem.entity.command;

import lombok.Data;

@Data
public class EmployeeUpdateCommand {
    private Integer grossSalary;

    public EmployeeUpdateCommand(Integer grossSalary) {
        this.grossSalary = grossSalary;
    }

    public EmployeeUpdateCommand() {

    }

    public Integer getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(Integer grossSalary) {
        this.grossSalary = grossSalary;
    }
}
