package com.git.activities.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.git.activities.entities.RepoDailyStatistics;

public interface DailyCommitsRepository extends JpaRepository<RepoDailyStatistics, Long> {

}

