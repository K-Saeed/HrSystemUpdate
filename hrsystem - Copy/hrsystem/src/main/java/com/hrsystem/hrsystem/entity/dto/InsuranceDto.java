package com.hrsystem.hrsystem.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class InsuranceDto {
    private Integer id;
    private Integer leavesYear;
    private Integer insuranceYears ;
    private Integer employeeId ;

    public InsuranceDto( Integer leavesYear, Integer insuranceYears, Integer employeeId) {
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
