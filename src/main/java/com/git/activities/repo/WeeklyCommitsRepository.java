package com.git.activities.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.git.activities.entities.RepoWeeklyStatistics;

@Repository
public interface WeeklyCommitsRepository extends JpaRepository<RepoWeeklyStatistics, Long> {

}
