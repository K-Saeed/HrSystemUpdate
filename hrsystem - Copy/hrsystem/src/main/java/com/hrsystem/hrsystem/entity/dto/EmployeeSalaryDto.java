package com.hrsystem.hrsystem.entity.dto;

import lombok.Data;

@Data
public class EmployeeSalaryDto {
    private Integer employeeid ;
    private Integer grossSalary ;
    private Double netSalary ;

    public EmployeeSalaryDto () {

    }
    public EmployeeSalaryDto (Integer employeeid , Integer grossSalary , Double netSalary) {
        this.employeeid=employeeid;
        this.grossSalary=grossSalary;
        this.netSalary=netSalary;

    }


    public void setGrossSalary(Integer grossSalary) {
        this.grossSalary = grossSalary;
    }

    public Integer getGrossSalary() {
        return grossSalary;
    }

    public void setNetSalary(Double netSalary) {
        this.netSalary = netSalary;
    }

    public Double getNetSalary() {
        return netSalary;
    }
}
