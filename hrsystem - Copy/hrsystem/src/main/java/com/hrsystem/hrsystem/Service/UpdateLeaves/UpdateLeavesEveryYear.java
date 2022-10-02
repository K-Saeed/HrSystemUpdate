package com.hrsystem.hrsystem.Service.UpdateLeaves;

import com.hrsystem.hrsystem.entity.Employee;
import com.hrsystem.hrsystem.entity.Insurance;
import com.hrsystem.hrsystem.repostiory.EmployeeRepository;
import com.hrsystem.hrsystem.repostiory.InsuranceRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLeavesEveryYear {

    private EmployeeRepository employeeRepository ;
    private InsuranceRepository insuranceRepository;
    @Scheduled(cron="0 0 0 12 1 ?")
    public void updateLeavesEveryYear () {
        Iterable<Employee> employees = employeeRepository.findAll();
        for (Employee employee:employees) {
            Insurance insurance = insuranceRepository.getInsuranceAndLeavesByEmployeeId(employee.getId());
            employee.setLeaves(insurance.getLeavesYear());
        }

    }
}
