package com.hrsystem.hrsystem.repostiory;

import com.hrsystem.hrsystem.entity.Salary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

public interface SalaryRepository extends CrudRepository <Salary,Integer> {
    @Query("SELECT s FROM Salary s WHERE s.employee.id =:id ")
    List<Salary> getSalaries(@PathVariable Integer id);

    @Query("SELECT s FROM Salary s WHERE s.sDate =:date and s.employee.id=:id ")
    Salary findByDate(LocalDate date,Integer id);
}
