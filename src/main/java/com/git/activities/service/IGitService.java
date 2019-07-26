package com.git.activities.service;

import java.util.List;

import com.git.activities.dto.GitRepositoriesResponse;

public interface IGitService {

	public List<GitRepositoriesResponse> getRepositoriesAndCommits(String ServiceName, String ownerName); 
	
}
