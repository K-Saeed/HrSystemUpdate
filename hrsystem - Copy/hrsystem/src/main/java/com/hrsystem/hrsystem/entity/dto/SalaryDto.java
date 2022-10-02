package com.hrsystem.hrsystem.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalaryDto {

    private LocalDate sDate;
    private Integer exceededLeaves;
    private Double taxes ;
    private Double insurance;
    private Double raises;
    private Double bonus ;
    private Double netSalary;
    private Double employeeId;
}
