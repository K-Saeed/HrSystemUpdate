package com.hrsystem.hrsystem.Service;

import com.hrsystem.hrsystem.Service.calculatesalary.CalculateSalary;
import com.hrsystem.hrsystem.Service.mapper.EmployeeMapper;
import com.hrsystem.hrsystem.entity.Employee;
import com.hrsystem.hrsystem.entity.Insurance;
import com.hrsystem.hrsystem.entity.LeavesHistory;
import com.hrsystem.hrsystem.entity.Salary;
import com.hrsystem.hrsystem.entity.command.InsuranceCommand;
import com.hrsystem.hrsystem.entity.command.LeavesCommand;
import com.hrsystem.hrsystem.entity.dto.*;
import com.hrsystem.hrsystem.repostiory.*;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@EnableScheduling
public class SalaryService {
    private EmployeeRepository employeeRepository;
    private TeamRepository teamRepository;
    private EmployeeMapper employeeMapper;
    private SalaryRepository salaryRepository;
    private InsuranceRepository insuranceRepository ;
    private LeavesHistoryRepository leavesHistoryRepository;
    private CalculateSalary calculateSalary ;
    public Iterable<SalaryDto> getSalaryHistoryOfEmployee(Integer employeeId) throws Exception {
        if (employeeRepository.findById(employeeId)!= null) {
            List<Salary> salaries = salaryRepository.getSalaries(employeeId);
            List <SalaryDto> salaryDtos = new ArrayList<>() ;
            for (int i=0; i<salaries.size(); i++ ){
                salaryDtos.add(employeeMapper.convertSalaryEntityToDto(salaries.get(i)));
            }
            return salaryDtos ;
        }else throw new Exception();
    }

    public LeavesEmployeeDto recordEmployeeLeaves(Integer employeeid, LeavesCommand leavesCommand) throws Exception {
        if (employeeRepository.findById(employeeid).get() != null) {
            Employee employee = employeeRepository.findById(employeeid).get();
            Integer calcLeaves = this.calculateEmployeeLeaves(employee, leavesCommand);
            this.setLeavesHistory(calcLeaves,employee);
            if (calcLeaves > 0) {
                employee.setLeaves(calcLeaves);
                employeeRepository.save(employee);
                return employeeMapper.convertEmployeeToLeavesDto(employee);

            }else {
                LocalDate date = LocalDate.now();
                if (salaryRepository.findByDate(date,employeeid)!= null){
                    Salary salary = salaryRepository.findByDate(date,employeeid);
                    salary.setExceededLeaves(calcLeaves);
                    employee.setLeaves(0);
                    return employeeMapper.convertEmployeeToLeavesDto(employee);
                }else {
                    Salary salary = new Salary();
                    salary.setExceededLeaves(calcLeaves);
                    salary.setEmployee(employee);
                    salary.setsDate(LocalDate.now());
                    salaryRepository.save(salary);
                    employee.setLeaves(0);
                    salary.setEmployee(employee);
                    return employeeMapper.convertEmployeeToLeavesDto(employee);
                }
            }

        } else
            throw new Exception("Id Not Found : " + employeeid);
    }

    private void setLeavesHistory(Integer calcLeaves, Employee employee) {
        LeavesHistory leavesHistory = new LeavesHistory();
        leavesHistory.setLeavesTotal(calcLeaves);
        leavesHistory.setEmployee(employee);
        leavesHistory.setlDate(LocalDate.now());
        leavesHistoryRepository.save(leavesHistory);
    }
    private Integer calculateEmployeeLeaves(Employee employee, LeavesCommand leavesCommand) {
        Integer leaves = employee.getLeaves();
        Integer leaves2 = leavesCommand.getLeaves();
        Integer leavesUp = leaves-leaves2;
        return leavesUp;
    }

    public InsuranceDto setEmployeeLeaves(InsuranceCommand insuranceCommand) {
        Insurance insurance = employeeMapper.convertInsuranceCommandToEntity(insuranceCommand);
        insuranceRepository.save(insurance);
        return employeeMapper.convertInsuranceEntitToDto(insurance);
    }
    public BonusEmployeeDto recordEmployeeBonus(Integer employeeId) throws Exception {
        Salary salary = new Salary();
        BonusEmployeeDto bonusEmployeeDto = new BonusEmployeeDto();
        if (employeeRepository.findById(employeeId).get() != null) {
            Employee employee = employeeRepository.findById(employeeId).get();
            Double calcBonus = this.calculateEmployeeBonus(employee);
            LocalDate date = LocalDate.now();
            if (salaryRepository.findByDate(date, employeeId) != null) {
                salary = salaryRepository.findByDate(date, employeeId);
                salary.setBonus(calcBonus);
                bonusEmployeeDto.setBonus(calcBonus);
                return bonusEmployeeDto;
            } else{
                salary.setBonus(calcBonus);
                salary.setsDate(LocalDate.now());
                salary.setEmployee(employee);
                salaryRepository.save(salary);
                bonusEmployeeDto.setBonus(calcBonus);
                return bonusEmployeeDto ;

            }

        } else throw new Exception("Id Not Found : " + employeeId);

    }

    private Double calculateEmployeeBonus(Employee employee) {
        return employee.getGrossSallary();
    }

    public RaisesEmployeeDto recordEmployeeRaises(Integer employeeId) throws Exception {
        Salary salary = new Salary();
        RaisesEmployeeDto raisesEmployeeDto = new RaisesEmployeeDto();
        if (employeeRepository.findById(employeeId).get() != null) {
            Employee employee = employeeRepository.findById(employeeId).get();
            Double calcRaises = this.calculateEmployeeRaises(employee);
            LocalDate date = LocalDate.now();
            if (salaryRepository.findByDate(date, employeeId) != null) {
                salary = salaryRepository.findByDate(date, employeeId);
                salary.setRaises(calcRaises);
                employee.setGrossSallary(calcRaises);
                raisesEmployeeDto.setRaises(calcRaises);
                return raisesEmployeeDto;
            } else{
                salary.setRaises(calcRaises);
                salary.setsDate(LocalDate.now());
                salary.setEmployee(employee);
                salaryRepository.save(salary);
                raisesEmployeeDto.setRaises(calcRaises);
                return raisesEmployeeDto ;

            }

        } else throw new Exception("Id Not Found : " + employeeId);


    }

    private Double calculateEmployeeRaises(Employee employee) {
        Double salary =employee.getGrossSallary();
        double raises = (salary*.20)+salary;
        return raises;
    }

    public List<SalaryDto> getEmployeesSalary() throws Exception {
        calculateSalary.calculateSalaryEveryMonth();
        Iterable<Salary> salaries = new ArrayList<>();
        List<SalaryDto>salaryDtos= new ArrayList<>();
        salaries=salaryRepository.findAll();
        for (Salary salary:salaries ) {
            salaryDtos.add(employeeMapper.convertSalaryEntityToDto(salary));
        }
        return salaryDtos;
    }
}
