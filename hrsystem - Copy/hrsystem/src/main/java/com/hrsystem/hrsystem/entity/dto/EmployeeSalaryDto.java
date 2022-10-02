package com.hrsystem.hrsystem.entity.dto;

import lombok.Data;

@Data
public class EmployeeSalaryDto {
    private Integer employeeid ;
    private Double grossSalary ;
    private Double netSalary ;

    public EmployeeSalaryDto () {

    }
    public EmployeeSalaryDto (Integer employeeid , Double grossSalary , Double netSalary) {
        this.employeeid=employeeid;
        this.grossSalary=grossSalary;
        this.netSalary=netSalary;

    }


    public void setGrossSalary(Double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public Double getGrossSalary() {
        return grossSalary;
    }

    public void setNetSalary(Double netSalary) {
        this.netSalary = netSalary;
    }

    public Double getNetSalary() {
        return netSalary;
    }
}
