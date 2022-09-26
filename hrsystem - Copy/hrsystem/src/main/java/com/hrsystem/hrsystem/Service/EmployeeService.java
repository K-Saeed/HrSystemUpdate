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
import com.hrsystem.hrsystem.repostiory.DepartmentRepository;
import com.hrsystem.hrsystem.repostiory.EmployeeRepository;
import com.hrsystem.hrsystem.repostiory.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    EmployeeMapper employeeMapper;

    public EmployeeDto newEmploueeCreater(EmployeeCommand employeeCommand) throws Exception {
       /* EmployeeService employeeService = new EmployeeService();
        Boolean checker = employeeService.employeeHasNotManagerChecker();*/
        //if ( checker== false) {
            Integer managerId = employeeCommand.getManagerId();
            Employee employee = employeeMapper.employeeComandConvertToEntity( employeeCommand );
            employee = employeeRepository.save( employee );
            List<Employee> list = new ArrayList<>();
            list.add( employee );
            employee.setEmployees( list );
            EmployeeDto employeeDto = employeeMapper.employeeConvertToEmployeeDto( employee, employeeCommand );
            return employeeDto;
//        /*}else
//            throw new Exception("Manager can't be null");
    }

    public EmployeeDto EmployeeGeterByID(Integer id) throws Exception {
       if (employeeRepository.findById(id).get()!=null) {
           Employee employee =employeeRepository.findById(id).get();
           EmployeeDto employeeDto = employeeMapper.employeeEntityConvertToDto(employee );
           return employeeDto;
       }else
       throw  new Exception( "id: "+id );
    }

    public EmployeeUpdateDto employeeUpdater(Integer id, EmployeeUpdateCommand employeeUpdateCommand) throws Exception {
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

    public List<EmployeeFindAllDto> allEmployeesRelatedToOneManager(Integer managerid) throws Exception {
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

    public List<EmployeeFindAllDto> employeesGeterByTeamid(Integer teamid) throws Exception {
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

    public EmployeeSalaryDto employeeGetNetSalary(Integer employeeid) throws Exception {
        Employee employee = employeeRepository.findById( employeeid ).get();
        if (employee!=null) {
            Integer grossSalary = employee.getGrossSallary();
            double netSalary = grossSalary - (grossSalary * .15) - 500;
            EmployeeSalaryDto employeeSalaryDto = new EmployeeSalaryDto();
            employeeSalaryDto.setEmployeeid( employeeid );
            employeeSalaryDto.setGrossSalary( grossSalary );
            employeeSalaryDto.setNetSalary( netSalary );
            return employeeSalaryDto;
        }else
            throw new Exception("Id Not Found : "+employeeid );
    }

    public String EmployeeDelterByID(Integer employeeid) throws Exception {
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

    public List<EmployeeFindAllDto> allEmployeesHierarchical (Integer managerid) throws Exception {
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

    public void getAllEmployees(Employee manager, List<Employee> employees) {
        if (manager.getEmployees()  == null || manager.getEmployees().size() == 0)
            return;
            employees.addAll( manager.getEmployees() );
            for (int i = 0; i < employees.size(); i++) {
                getAllEmployees( employees.get( i ), employees );
            }
    }
    public Boolean employeeHasNotManagerChecker () throws Exception{
        if (employeeRepository.searchAllEmployees() != null) {
            List <Employee> employees = employeeRepository.searchAllEmployees();
            if (employees.size() >= 2) {
                return true;
            }else
                return false ;
        }else
            throw new Exception();
    }
}
