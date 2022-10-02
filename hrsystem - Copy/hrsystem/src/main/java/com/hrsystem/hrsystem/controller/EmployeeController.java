package com.hrsystem.hrsystem.controller;

import com.hrsystem.hrsystem.Service.EmployeeService;
import com.hrsystem.hrsystem.entity.EmployeeFindAllDto;
import com.hrsystem.hrsystem.entity.command.EmployeeCommand;
import com.hrsystem.hrsystem.entity.command.EmployeeUpdateCommand;
import com.hrsystem.hrsystem.entity.dto.*;
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
        return employeeService.createNewEmployee(employeeCommand);
    }
    @GetMapping  (value ="/{employeeid}")
    public @ResponseBody EmployeeDto findEmployeeByID (@PathVariable Integer employeeid) throws Exception {
        return employeeService.getEmployeeByID(employeeid);
    }
    @PutMapping (value = "/update/{employeeid}")
    public @ResponseBody EmployeeUpdateDto updateEmployee (@PathVariable  Integer employeeid , @RequestBody EmployeeUpdateCommand employeeUpdateCommand) throws Exception {
        return employeeService.updateEmployee(employeeid,employeeUpdateCommand);
    }

    @GetMapping (value = "/manager/{managerid}")
    public  @ResponseBody List <EmployeeFindAllDto>  getEmployeesByManagerid (@PathVariable Integer managerid) throws Exception {
        return employeeService.getAllEmployeesRelatedToOneManager(managerid);
    }

    @GetMapping (value = "/team/{teamid}")
    public  @ResponseBody List<EmployeeFindAllDto> getEmployeesByTeamid(@PathVariable Integer teamid) throws Exception {
        return employeeService.getEmployeesByTeamid(teamid);
    }

    @GetMapping (value = "/salary/{employeeid}")
    public EmployeeSalaryDto getEmployeeNetSalary(@PathVariable Integer employeeid) throws Exception {
        return employeeService.getEmployeeNetSalary(employeeid);
    }

    @DeleteMapping (value =  "/employee/{employeeid}")
    public Object EmployeeDelterByID(@PathVariable Integer employeeid) throws Exception {
        return employeeService.deleteEmployeeByID(employeeid);
    }
    @GetMapping (value ="/manager/hierarchical/{managerid}")
    public  @ResponseBody List <EmployeeFindAllDto>  allEmployeesHierarchical (@PathVariable Integer managerid) throws Exception {
        return employeeService.getAllEmployeesHierarchical(managerid);
    }


}
