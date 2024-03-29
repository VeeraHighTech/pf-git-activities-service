package com.git.activity.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GitApiServiceConfig {

	@Value("${git.api.repos.url}")
	private String gitRepos;

	@Value("${git.api.repo.stats.url}")
	private String gitRepoStats;
	
	@Value("${git.api.repo.url}")
	private String gitRepoURL;

	public String getGitRepos() {
		return gitRepos;
	}

	public void setGitRepos(String gitRepos) {
		this.gitRepos = gitRepos;
	}

	public String getGitRepoStats() {
		return gitRepoStats;
	}

	public void setGitRepoStats(String gitRepoStats) {
		this.gitRepoStats = gitRepoStats;
	}

	public String getGitRepoURL() {
		return gitRepoURL;
	}

	public void setGitRepoURL(String gitRepoURL) {
		this.gitRepoURL = gitRepoURL;
	};
	
	
}
