package com.hrsystem.hrsystem.repostiory;

import com.hrsystem.hrsystem.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    @Query(value = "SELECT e FROM employee e WHERE e.manager_id is null" ,nativeQuery = true)
    List<Employee> searchAllEmployees();
}
