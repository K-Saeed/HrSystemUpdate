package com.hrsystem.hrsystem.Service;

import com.hrsystem.hrsystem.Service.calculatesalary.CalculateSalary;
import com.hrsystem.hrsystem.Service.mapper.EmployeeMapper;
import com.hrsystem.hrsystem.entity.Employee;
import com.hrsystem.hrsystem.entity.Salary;
import com.hrsystem.hrsystem.entity.dto.BonusEmployeeDto;
import com.hrsystem.hrsystem.entity.dto.RaisesEmployeeDto;
import com.hrsystem.hrsystem.entity.dto.SalaryDto;
import com.hrsystem.hrsystem.repostiory.*;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@EnableScheduling
public class SalaryService {
    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;
    private SalaryRepository salaryRepository;
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


    public BonusEmployeeDto recordEmployeeBonus(Integer employeeId) throws Exception {
        Salary salary = new Salary();
        BonusEmployeeDto bonusEmployeeDto = new BonusEmployeeDto();
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        Double calcBonus = this.calculateEmployeeBonus(employee);
        Month month = Month.from(LocalDate.now());
        if (salaryRepository.findByDate(month.getValue(), employeeId) != null) {
            salary = salaryRepository.findByDate(month.getValue(), employeeId);
            if (salary.getBonus()==null) {
                salary = this.setSalary(calcBonus,employee);
                salaryRepository.save(salary);
                bonusEmployeeDto.setBonus(calcBonus);
                return bonusEmployeeDto;
            }else {
                Double newBonus = salary.getBonus()+calcBonus;
                salary = this.setSalary(newBonus,employee);
                salaryRepository.save(salary);
                bonusEmployeeDto.setBonus(newBonus);
                return bonusEmployeeDto;
            }
        } else {
            salary = this.setSalary(calcBonus,employee);
            salaryRepository.save(salary);
            bonusEmployeeDto.setBonus(calcBonus);
            return bonusEmployeeDto;
        }


    }

    private Salary setSalary(Double calcBonus, Employee employee) {
        Salary salary = new Salary();
        salary.setBonus(calcBonus);
        salary.setEmployee(employee);
        salary.setsDate(LocalDate.now());
        return salary;
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
            Month month =Month.from(LocalDate.now());
            if (salaryRepository.findByDate(month.getValue(), employeeId) != null) {
                salary = salaryRepository.findByDate(month.getValue(), employeeId);
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
