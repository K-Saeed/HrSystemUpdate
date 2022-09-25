package com.hrsystem.hrsystem.repostiory;

import com.hrsystem.hrsystem.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
