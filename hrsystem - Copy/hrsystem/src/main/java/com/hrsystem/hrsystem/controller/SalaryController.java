package com.hrsystem.hrsystem.controller;

import com.hrsystem.hrsystem.Service.SalaryService;
import com.hrsystem.hrsystem.entity.dto.BonusEmployeeDto;
import com.hrsystem.hrsystem.entity.dto.RaisesEmployeeDto;
import com.hrsystem.hrsystem.entity.dto.SalaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/salary")
public class SalaryController {
    @Autowired
    SalaryService salaryService;
    @GetMapping(value ="/salary/history/{employeeId}")
    public  @ResponseBody
    Iterable <SalaryDto>  getSalaryHistoryOfEmployee (@PathVariable Integer employeeId) throws Exception {
        return salaryService.getSalaryHistoryOfEmployee(employeeId);
    }
    @PutMapping (value = "/record/bonus/{employeeId}")
    public @ResponseBody
    BonusEmployeeDto recordEmployeeBonus (@PathVariable  Integer employeeId ) throws Exception {
        return salaryService.recordEmployeeBonus(employeeId);
    }
    @PutMapping (value = "/record/raises/{employeeId}")
    public @ResponseBody
    RaisesEmployeeDto recordEmployeeRaises (@PathVariable  Integer employeeId ) throws Exception {
        return salaryService.recordEmployeeRaises(employeeId);
    }
    @GetMapping (value = "/all")
    public @ResponseBody List<SalaryDto> getEmployeesSalary () throws Exception {
        return salaryService.getEmployeesSalary();
    }
}
