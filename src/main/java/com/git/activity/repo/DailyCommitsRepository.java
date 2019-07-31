package com.git.activity.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.git.activity.entities.DailyStatistics;

public interface DailyCommitsRepository extends JpaRepository<DailyStatistics, Long> {

}

