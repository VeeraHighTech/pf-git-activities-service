package com.git.activities.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.git.activities.entities.Repositories;

@Repository
public interface ReposRepository extends JpaRepository<Repositories, Long> {

	public Repositories findByRepoName(String repoName);
}
