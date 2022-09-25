package com.hrsystem.hrsystem.repostiory;

import com.hrsystem.hrsystem.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Integer> {
}
