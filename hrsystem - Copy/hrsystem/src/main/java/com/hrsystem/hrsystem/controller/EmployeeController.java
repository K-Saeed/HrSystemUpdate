package com.hrsystem.hrsystem.controller;

import com.hrsystem.hrsystem.Service.EmployeeService;
import com.hrsystem.hrsystem.entity.EmployeeFindAllDto;
import com.hrsystem.hrsystem.entity.command.EmployeeCommand;
import com.hrsystem.hrsystem.entity.command.EmployeeUpdateCommand;
import com.hrsystem.hrsystem.entity.dto.EmployeeDto;
import com.hrsystem.hrsystem.entity.dto.EmployeeSalaryDto;
import com.hrsystem.hrsystem.entity.dto.EmployeeUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping (value = "/add")
    public @ResponseBody EmployeeDto addNewEmployee (@RequestBody EmployeeCommand employeeCommand) throws Exception {
        return employeeService.newEmploueeCreater(employeeCommand);
    }
    @GetMapping  (value ="/{employeeid}")
    public @ResponseBody EmployeeDto findEmployeeByID (@PathVariable Integer employeeid) throws Exception {
        return employeeService.EmployeeGeterByID(employeeid);
    }
    @PutMapping (value = "/update/{employeeid}")
    public @ResponseBody EmployeeUpdateDto updateEmployee (@PathVariable  Integer employeeid , @RequestBody EmployeeUpdateCommand employeeUpdateCommand) throws Exception {
        return employeeService.employeeUpdater(employeeid,employeeUpdateCommand);
    }

    @GetMapping (value = "/manager/{managerid}")
    public  @ResponseBody List <EmployeeFindAllDto>  getEmployeesByManagerid (@PathVariable Integer managerid) throws Exception {
        return employeeService.allEmployeesRelatedToOneManager(managerid);
    }

    @GetMapping (value = "/team/{teamid}")
    public  @ResponseBody List<EmployeeFindAllDto> getEmployeesByTeamid(@PathVariable Integer teamid) throws Exception {
        return employeeService.employeesGeterByTeamid(teamid);
    }

    @GetMapping (value = "/salary/{employeeid}")
    public EmployeeSalaryDto getEmployeeNetSalary(@PathVariable Integer employeeid) throws Exception {
        return employeeService.employeeGetNetSalary(employeeid);
    }

    @DeleteMapping (value =  "/employee/{employeeid}")
    public String EmployeeDelterByID( @PathVariable Integer employeeid) throws Exception {
        return employeeService.EmployeeDelterByID(employeeid);
    }
    @GetMapping (value ="/manager/hierarchical/{managerid}")
    public  @ResponseBody List <EmployeeFindAllDto>  allEmployeesHierarchical (@PathVariable Integer managerid) throws Exception {
        return employeeService.allEmployeesHierarchical(managerid);
    }

}
