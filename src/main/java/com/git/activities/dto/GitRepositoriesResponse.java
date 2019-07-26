package com.git.activities.dto;

import java.util.ArrayList;
import java.util.List;

public class GitRepositoriesResponse {

	
    private Long id;
    private String repoName;
    List<GitDailyCommitsResponse> gitdailycommits = new ArrayList<>();
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRepoName() {
		return repoName;
	}
	public void setRepoName(String repoName) {
		this.repoName = repoName;
	}
	public List<GitDailyCommitsResponse> getGitdailycommits() {
		return gitdailycommits;
	}
	public void setGitdailycommits(List<GitDailyCommitsResponse> gitdailycommits) {
		this.gitdailycommits = gitdailycommits;
	}
	
	
	@Override
	public String toString() {
		return "GitRepositoriesResponse [id=" + id + ", repoName=" + repoName + ", gitdailycommits=" + gitdailycommits
				+ "]";
	}
    
}

    