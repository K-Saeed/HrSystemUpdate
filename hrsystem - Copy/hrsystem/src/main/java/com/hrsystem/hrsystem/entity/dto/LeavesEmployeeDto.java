package com.hrsystem.hrsystem.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class LeavesEmployeeDto {
    private Integer exceedLeaves ;
    private Integer leaves ;
    private Integer employeeId;
    private LocalDate startLeavesDate ;
    private LocalDate backToWorkDate ;
    public LeavesEmployeeDto(Integer leaves, Integer employeeId, LocalDate startLeavesDate, LocalDate backToWorkDate ,Integer exceedLeaves ) {
        this.leaves = leaves;
        this.employeeId = employeeId;
        this.startLeavesDate = startLeavesDate;
        this.backToWorkDate = backToWorkDate;
        this.exceedLeaves = exceedLeaves;
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

    public LocalDate getStartLeavesDate() {
        return startLeavesDate;
    }

    public void setStartLeavesDate(LocalDate startLeavesDate) {
        this.startLeavesDate = startLeavesDate;
    }

    public LocalDate getBackToWorkDate() {
        return backToWorkDate;
    }

    public void setBackToWorkDate(LocalDate backToWorkDate) {
        this.backToWorkDate = backToWorkDate;
    }

    public Integer getExceedLeaves() {
        return exceedLeaves;
    }

    public void setExceedLeaves(Integer exceedLeaves) {
        this.exceedLeaves = exceedLeaves;
    }
}
