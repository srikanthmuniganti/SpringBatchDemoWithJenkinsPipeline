package com.dev.batch.appln.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev.batch.appln.models.MatchOutput;

public interface BatchRepository extends JpaRepository<MatchOutput, Long> {

	@Query(nativeQuery = true, value = "select distinct team1 from match_output order by team1;")
	List<String> findByTeam1();

}
