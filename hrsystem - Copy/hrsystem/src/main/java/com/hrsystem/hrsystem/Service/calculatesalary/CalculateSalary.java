package com.hrsystem.hrsystem.Service.calculatesalary;

import com.hrsystem.hrsystem.entity.Employee;
import com.hrsystem.hrsystem.entity.Salary;
import com.hrsystem.hrsystem.repostiory.EmployeeRepository;
import com.hrsystem.hrsystem.repostiory.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;

@Component
public class CalculateSalary {
    @Autowired
    private EmployeeRepository employeeRepository ;
    @Autowired
    private SalaryRepository salaryRepository;

    @Scheduled(cron="0 0 0 1 1/1 *")
    public void calculateSalaryEveryMonth () throws Exception {
        Salary salary = new Salary();
        Double salaryAfterBonus ;
        Double salaryAfterExceeded ;
        if (employeeRepository.findAll()!=null) {
            Iterable<Employee> employees = employeeRepository.findAll();
            for (Employee employee : employees) {
                Double grossSalary = employee.getGrossSallary();
                Double grossAfterTaxesAndInsurance = this.calculateGrossWithOutTaxesAndInsurance(grossSalary);
                Month month =Month.from(LocalDate.now());
                Integer employeeId =employee.getId();
                if (salaryRepository.findByDate(month.getValue(),employeeId)!= null) {
                    salary = salaryRepository.findByDate(month.getValue(), employee.getId());
                    salary.setTaxes((employee.getGrossSallary())*.15);
                    salary.setInsurance(500.0);
                    if (salary.getExceededLeaves()!=null){
                        salaryAfterExceeded = this.calculateExceeded(grossSalary,salary,grossAfterTaxesAndInsurance);
                        if (salary.getBonus()!=null){
                            salaryAfterBonus=salaryAfterExceeded+grossSalary;
                        }
                    }else {
                        if (salary.getBonus()!=null){
                            salaryAfterBonus=(salary.getBonus())+grossSalary;
                        }
                    }
                }else {
                    salary.setTaxes((employee.getGrossSallary())*.15);
                    salary.setInsurance(500.0);
                    salary.setNetSalary(grossAfterTaxesAndInsurance);
                    salary.setsDate(LocalDate.now());
                    salary.setEmployee(employee);
                    salaryRepository.save(salary);
                }
            }
        }else throw new Exception();

    }

    private Double calculateExceeded(Double grossSalary, Salary salary, Double grossAfterTaxesAndInsurance) {
        Integer exceeded = salary.getExceededLeaves();
        Double money = (grossSalary/22)*exceeded;
        money = grossAfterTaxesAndInsurance + money;
        return money;
    }

    private Double calculateGrossWithOutTaxesAndInsurance(Double grossSalary) {
        Double grossAfterTaxesAndInsurance = (grossSalary-(grossSalary*.15))-500;
        return grossAfterTaxesAndInsurance;
    }


}
