package com.hrsystem.hrsystem.repostiory;

import com.hrsystem.hrsystem.entity.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, Integer> {
}
