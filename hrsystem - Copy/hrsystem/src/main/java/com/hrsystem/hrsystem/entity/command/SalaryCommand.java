package com.hrsystem.hrsystem.entity.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalaryCommand {
    private LocalDate sDate;
    private Integer exceededLeaves;
    private Integer taxes ;
    private Integer insurance;
    private Integer raises;
    private Integer bonus ;
    private Integer netSalary;
    private Integer employeeId;

}
