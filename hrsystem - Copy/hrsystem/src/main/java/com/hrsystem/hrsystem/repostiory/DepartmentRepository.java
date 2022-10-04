package com.hrsystem.hrsystem.repostiory;

import com.hrsystem.hrsystem.entity.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository  extends CrudRepository<Department, Integer> {

}
