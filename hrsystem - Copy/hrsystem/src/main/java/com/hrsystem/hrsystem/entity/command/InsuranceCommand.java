package com.hrsystem.hrsystem.entity.command;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InsuranceCommand {
    private Integer id;
    private Integer leavesYear;
    private Integer insuranceYears ;
    private Integer employeeId ;

    public InsuranceCommand( Integer leavesYear, Integer insuranceYears, Integer employeeId) {
        this.leavesYear = leavesYear;
        this.insuranceYears = insuranceYears;
        this.employeeId = employeeId;
    }


    public Integer getLeavesYear() {
        return leavesYear;
    }

    public void setLeavesYear(Integer leavesYear) {
        this.leavesYear = leavesYear;
    }

    public Integer getInsuranceYears() {
        return insuranceYears;
    }

    public void setInsuranceYears(Integer insuranceYears) {
        this.insuranceYears = insuranceYears;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}
