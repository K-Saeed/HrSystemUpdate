package com.hrsystem.hrsystem.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LeavesEmployeeDto {
    private Integer leaves ;

    public LeavesEmployeeDto(Integer leaves) {
        this.leaves = leaves;
    }

    public Integer getLeaves() {
        return leaves;
    }

    public void setLeaves(Integer leaves) {
        this.leaves = leaves;
    }
}
