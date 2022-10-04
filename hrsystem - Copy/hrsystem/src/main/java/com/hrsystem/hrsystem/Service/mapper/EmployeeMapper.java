package com.hrsystem.hrsystem.Service.mapper;

import com.hrsystem.hrsystem.entity.*;
import com.hrsystem.hrsystem.entity.command.EmployeeCommand;
import com.hrsystem.hrsystem.entity.command.LeavesCommand;
import com.hrsystem.hrsystem.entity.dto.EmployeeDto;
import com.hrsystem.hrsystem.entity.dto.EmployeeUpdateDto;
import com.hrsystem.hrsystem.entity.dto.LeavesEmployeeDto;
import com.hrsystem.hrsystem.entity.dto.SalaryDto;
import com.hrsystem.hrsystem.repostiory.DepartmentRepository;
import com.hrsystem.hrsystem.repostiory.EmployeeRepository;
import com.hrsystem.hrsystem.repostiory.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Service
public class EmployeeMapper {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private TeamRepository teamRepository;

    public Employee employeeComandConvertToEntity(EmployeeCommand employeeCommand) {
        Employee employee = modelMapper.map( employeeCommand, Employee.class );
        Integer dept_id = employeeCommand.getDepartmentId();
        Integer team_id = employeeCommand.getTeamId();
        Integer manag_id = employeeCommand.getManagerId();
        Department department = departmentRepository.findById(dept_id).get();
        Team team = teamRepository.findById(team_id).get();
        Employee manager = employeeRepository.findById(manag_id).get();
        employee.setDepartment( department );
        employee.setTeam( team );
        employee.setManager( manager );
        return employee;
    }

    public EmployeeDto employeeConvertToEmployeeDto(Employee employee, EmployeeCommand employeeCommand) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto = modelMapper.map( employee, EmployeeDto.class );
        Department department = employee.getDepartment();
        int dept_id = department.getId();
        employeeDto.setDepartmentId( dept_id );
        Team team = employee.getTeam();
        int team_id = team.getId();
        employeeDto.setTeamId( team_id );
        Employee manager = employeeRepository.findById( employeeCommand.getManagerId() ).get();
        int manager_id = manager.getId();
        employeeDto.setManagerId( manager_id );
        return employeeDto;
    }

    public EmployeeDto employeeEntityConvertToDto(Employee employee) {
        modelMapper.getConfiguration().setAmbiguityIgnored( true );
        EmployeeDto employeeDto = modelMapper.map( employee, EmployeeDto.class );
        return employeeDto;
    }

    public EmployeeFindAllDto employeeConvertToEmployeeDto(Employee employee) {
        EmployeeFindAllDto employeeFindAllDto = modelMapper.map( employee, EmployeeFindAllDto.class );
        return employeeFindAllDto;
    }

    public EmployeeUpdateDto employeeEntityConvertToEmployeeUpdateDto(Employee employee) {

        EmployeeUpdateDto employeeUpdateDto = new EmployeeUpdateDto();
        employeeUpdateDto.setGrossSalary( employee.getGrossSallary() );
        return employeeUpdateDto;
    }

    public SalaryDto convertSalaryEntityToDto(Salary salary) {
        SalaryDto salaryDto = modelMapper.map(salary,SalaryDto.class);
        return salaryDto ;
    }

    public LeavesEmployeeDto convertEmployeeToLeavesDto(Employee employee) {
        return modelMapper.map(employee, LeavesEmployeeDto.class);
    }

    public LeavesEmployeeDto convertLeavesToDto(LeavesHistory leavesHistory, LeavesCommand leavesCommand) {
        LeavesEmployeeDto leavesEmployeeDto = new LeavesEmployeeDto();
        leavesEmployeeDto.setLeaves(leavesCommand.getLeaves());
        leavesEmployeeDto.setEmployeeId((leavesHistory.getEmployee()).getId());
        leavesEmployeeDto.setStartLeavesDate(leavesHistory.getlDate());
        leavesEmployeeDto.setExceedLeaves(leavesHistory.getLeaves());
        LocalDate date = leavesHistory.getlDate();
        LocalDate date2 = this.getBackToWorkDate(date,leavesCommand.getLeaves());
        leavesEmployeeDto.setBackToWorkDate(date2);
        return leavesEmployeeDto ;
    }

    private LocalDate getBackToWorkDate(LocalDate date, Integer leaves) {
        LocalDate date1 = date.plusDays(leaves);
        for (int i = 0; i < leaves; i++) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY) {
                date1 = date1.plusDays(1);
                leaves++;
            }
            date=date.plusDays(1);
        }
        return date1;
    }
}
