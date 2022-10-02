package com.hrsystem.hrsystem.Service;

import com.hrsystem.hrsystem.Service.mapper.EmployeeMapper;
import com.hrsystem.hrsystem.entity.Employee;
import com.hrsystem.hrsystem.entity.EmployeeFindAllDto;
import com.hrsystem.hrsystem.entity.Team;
import com.hrsystem.hrsystem.entity.command.EmployeeCommand;
import com.hrsystem.hrsystem.entity.command.EmployeeUpdateCommand;
import com.hrsystem.hrsystem.entity.dto.EmployeeDto;
import com.hrsystem.hrsystem.entity.dto.EmployeeSalaryDto;
import com.hrsystem.hrsystem.entity.dto.EmployeeUpdateDto;
import com.hrsystem.hrsystem.repostiory.*;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
@EnableScheduling
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private TeamRepository teamRepository;
    private EmployeeMapper employeeMapper;
    private SalaryRepository salaryRepository;
    private InsuranceRepository insuranceRepository ;
    private LeavesHistoryRepository leavesHistoryRepository;



    public EmployeeDto createNewEmployee(EmployeeCommand employeeCommand) throws Exception {
       Boolean checker = this.getEmployeeHasNotManagerChecker();
       if (!checker) {
            Employee employee = employeeMapper.employeeComandConvertToEntity( employeeCommand );
            employee = employeeRepository.save( employee );
            List<Employee> list = new ArrayList<>();
            list.add( employee );
            employee.setEmployees( list );
            EmployeeDto employeeDto = employeeMapper.employeeConvertToEmployeeDto( employee, employeeCommand );
            return employeeDto;
       }else
           throw new Exception("Manager can't be null");
    }

    public EmployeeDto getEmployeeByID(Integer id) throws Exception {
       if (employeeRepository.findById(id).get()!=null) {
           Employee employee =employeeRepository.findById(id).get();
           EmployeeDto employeeDto = employeeMapper.employeeEntityConvertToDto(employee );
           return employeeDto;
       }else
       throw  new Exception( "id: "+id );
    }

    public EmployeeUpdateDto updateEmployee(Integer id, EmployeeUpdateCommand employeeUpdateCommand) throws Exception {
        if (employeeRepository.findById(id).get() != null){
            Employee employee = employeeRepository.findById(id).get();
            employee.setGrossSallary(employeeUpdateCommand.getGrossSalary());
            employeeRepository.save(employee);
            EmployeeUpdateDto employeeUpdateDto = new EmployeeUpdateDto();
            employeeUpdateDto.setGrossSalary( employee.getGrossSallary() );
            return employeeUpdateDto ;
        }else
        throw new Exception("Id Not Found : "+ id);
    }

    public List<EmployeeFindAllDto> getAllEmployeesRelatedToOneManager(Integer managerid) throws Exception {
        if (employeeRepository.findById( managerid ).get()!= null) {
            Employee manager = employeeRepository.findById( managerid ).get();
            List<Employee> employees = manager.getEmployees();
            List<EmployeeFindAllDto> employeeFindAllDtos = new ArrayList<>();
            for (int i = 0; i < employees.size(); i++) {

                employeeFindAllDtos.add( employeeMapper.employeeConvertToEmployeeDto( employees.get( i ) ) );
            }
            return employeeFindAllDtos;
        }else
            throw new Exception( "Id Not Found : " +managerid);
    }

    public List<EmployeeFindAllDto> getEmployeesByTeamid(Integer teamid) throws Exception {
        if (teamRepository.findById(teamid).get() !=null) {
            Team team = teamRepository.findById(teamid).get();
            List<Employee> employees = team.getEmployees();
            List<EmployeeFindAllDto> employeeFindAllDtos = new ArrayList<>();
            for (int i = 0; i < employees.size(); i++) {
                employeeFindAllDtos.add( employeeMapper.employeeConvertToEmployeeDto( employees.get( i ) ) );
            }
            return employeeFindAllDtos ;
        }else
            throw new  Exception( "Id Not Found : "+teamid );
    }

    public EmployeeSalaryDto getEmployeeNetSalary(Integer employeeid) throws Exception {
        Employee employee = employeeRepository.findById( employeeid ).get();
        if (employee!=null) {
            Double grossSalary = employee.getGrossSallary();
            double netSalary = grossSalary - (grossSalary * .15) - 500;
            EmployeeSalaryDto employeeSalaryDto = new EmployeeSalaryDto();
            employeeSalaryDto.setEmployeeid( employeeid );
            employeeSalaryDto.setGrossSalary( grossSalary );
            employeeSalaryDto.setNetSalary( netSalary );
            return employeeSalaryDto;
        }else
            throw new Exception("Id Not Found : "+employeeid );
    }

    public String deleteEmployeeByID(Integer employeeid) throws Exception {
        Employee employee = employeeRepository.findById( employeeid ).get();
        if (employee!=null) {
            Employee manager = employee.getManager();
            if (manager!= null) {
                List<Employee> employees = employee.getEmployees();
                if (employees!=null) {
                    for (int i = 0; i < employees.size(); i++) {
                        Employee em = employees.get( i );
                        em.setManager( manager );
                    }
                employeeRepository.delete( employee );
                    String msg = "The employee deleted";
                    return msg;
                }else{
                    employeeRepository.delete( employee );
                    String msg = "The employee deleted";
                    return msg;
                }
            }else throw new Exception("Employee hasn't manager");

        }else throw new Exception("Id Not Found : " + employeeid);
    }

    public List<EmployeeFindAllDto> getAllEmployeesHierarchical(Integer managerid) throws Exception {
        Employee manager = employeeRepository.findById( managerid ).get();
        List<Employee> employees = new ArrayList<>();
        if (manager == null) {
            throw new Exception();
        }
        getAllEmployees(manager, employees);
        List <EmployeeFindAllDto> employeeFindAllDtos = new ArrayList<>();
        for (int i = 0; i < employees.size(); i++) {
            employeeFindAllDtos.add( employeeMapper.employeeConvertToEmployeeDto( employees.get( i ) ) );
        }
        return employeeFindAllDtos;
    }

    private void getAllEmployees(Employee manager, List<Employee> employees) {
        if (manager.getEmployees()  == null || manager.getEmployees().size() == 0)
            return;
            employees.addAll( manager.getEmployees() );
            for (int i = 0; i < employees.size(); i++) {
                getAllEmployees( employees.get( i ), employees );
            }
    }
    private Boolean getEmployeeHasNotManagerChecker() throws Exception{
        if (employeeRepository.getAllEmployees() != null) {
            List <Employee> employees = employeeRepository.getAllEmployees();
            if (employees.size() >= 2) {
                return true;
            }else
                return false ;
        }else
            throw new Exception();
    }

}
