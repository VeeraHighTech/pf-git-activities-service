package com.git.activity.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.git.activity.entities.WeeklyStatistics;

@Repository
public interface WeeklyCommitsRepository extends JpaRepository<WeeklyStatistics, Long> {
	
	@Query("SELECT ws FROM WeeklyStatistics  ws WHERE ws.repo.ownerName = ?1")
	List<WeeklyStatistics> findByRepo(String repoName);

}
