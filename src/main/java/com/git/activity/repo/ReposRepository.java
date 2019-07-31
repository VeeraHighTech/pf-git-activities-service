package com.git.activity.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.git.activity.entities.RepoDetails;

@Repository
public interface ReposRepository extends JpaRepository<RepoDetails, Long> {

	List<RepoDetails> findByOwnerName(String ownerName);
	
	List<RepoDetails> findByOwnerNameAndName(String ownerName, String repoName);

}
