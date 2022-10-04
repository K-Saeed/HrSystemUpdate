package com.hrsystem.hrsystem.repostiory;

import com.hrsystem.hrsystem.entity.LeavesHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LeavesHistoryRepository extends CrudRepository<LeavesHistory, Integer> {
    @Query ("SELECT SUM(l.leaves) FROM LeavesHistory l" +
            " WHERE l.employee.id =:employeeId AND YEAR(l.lDate)=:year" +
            " GROUP BY l.employee.id ")
    Integer getAllLeavesOfEmployee(@Param("employeeId") Integer employeeId,
                                   @Param("year") int year);
}