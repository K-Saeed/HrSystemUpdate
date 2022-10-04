package com.hrsystem.hrsystem.repostiory;

import com.hrsystem.hrsystem.entity.Salary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryRepository extends CrudRepository <Salary,Integer> {
    @Query("SELECT s FROM Salary s WHERE s.employee.id =:id ")
    List<Salary> getSalaries(@Param("id") Integer id);

    @Query("SELECT s FROM Salary s WHERE " +
            "MONTH(s.sDate)=:date" +
            " and s.employee.id=:id ")
    Salary findByDate(@Param("date")Integer date,@Param("id")Integer id);
}
