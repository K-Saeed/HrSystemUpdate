package com.hrsystem.hrsystem.controller;


import com.hrsystem.hrsystem.Service.LeavesService;
import com.hrsystem.hrsystem.entity.command.LeavesCommand;
import com.hrsystem.hrsystem.entity.dto.LeavesEmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/leaves")
public class LeaveHistoryController {

    @Autowired
    LeavesService leavesService ;

    @PutMapping(value = "/record/{employeeId}")
    public @ResponseBody
    LeavesEmployeeDto recordEmployeeLeaves (@PathVariable Integer employeeId , @RequestBody LeavesCommand leavesCommand) throws Exception {
        return leavesService.recordEmployeeLeaves(employeeId,leavesCommand);
    }
}
