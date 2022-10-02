package com.hrsystem.hrsystem.repostiory;

import com.hrsystem.hrsystem.entity.Insurance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

public interface InsuranceRepository extends CrudRepository<Insurance, Integer> {
    @Query ("SELECT ins FROM Insurance ins WHERE ins.employee.id =:employee_id")
    Insurance getInsuranceAndLeavesByEmployeeId (@PathVariable Integer employee_id);
}