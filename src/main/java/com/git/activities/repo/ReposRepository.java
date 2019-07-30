package com.git.activities.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.git.activities.entities.RepoDetails;

@Repository
public interface ReposRepository extends JpaRepository<RepoDetails, Long> {

	//public RepoDetails findByRepoName(String repoName);
}
