package com.hrsystem.hrsystem.repostiory;

import com.hrsystem.hrsystem.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    @Query("SELECT e FROM Employee e WHERE e.manager.id is null")
    List<Employee> getAllEmployees();

//   @Query(value = "SELECT e.id , e.fName ,e.manager.id FROM Employee e JOIN Employee on e.id=e.manager.id ")
//   List<Employee> getAllEmployeesHi();



}
