package com.git.activities.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.git.activities.entities.DailyStatistics;

public interface DailyCommitsRepository extends JpaRepository<DailyStatistics, Long> {

}

