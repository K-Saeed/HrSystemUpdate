package com.hrsystem.hrsystem.Service.mapper;

import com.hrsystem.hrsystem.entity.*;
import com.hrsystem.hrsystem.entity.command.EmployeeCommand;
import com.hrsystem.hrsystem.entity.command.InsuranceCommand;
import com.hrsystem.hrsystem.entity.dto.*;
import com.hrsystem.hrsystem.repostiory.DepartmentRepository;
import com.hrsystem.hrsystem.repostiory.EmployeeRepository;
import com.hrsystem.hrsystem.repostiory.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Insurance convertInsuranceCommandToEntity(InsuranceCommand insuranceCommand) {
        Insurance insurance =modelMapper.map(insuranceCommand,Insurance.class);
        Integer id = insuranceCommand.getEmployeeId();
        Employee employee = employeeRepository.findById(id).get();
        insurance.setEmployee(employee);
        return insurance ;
    }

    public InsuranceDto convertInsuranceEntitToDto(Insurance insurance) {
        InsuranceDto insuranceDto = modelMapper.map(insurance,InsuranceDto.class);
        Employee employee = insurance.getEmployee();
        insuranceDto.setEmployeeId(employee.getId());
        return insuranceDto;
    }

}
