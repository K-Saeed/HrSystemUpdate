package com.hrsystem.hrsystem.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LeavesEmployeeDto {
    private Integer leaves ;
    private Integer employeeId;
    public LeavesEmployeeDto(Integer leaves ,Integer employeeId) {
        this.leaves = leaves;
        this.employeeId = employeeId;
    }

    public Integer getLeaves() {
        return leaves;
    }

    public void setLeaves(Integer leaves) {
        this.leaves = leaves;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}
