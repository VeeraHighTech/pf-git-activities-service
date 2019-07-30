package com.git.activities.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GitApiServiceConfig {

	@Value("${git.api.repos.url}")
	private String gitApiServiceRepoUrl;

	@Value("${git.api.repo.stats.url}")
	private String gitApiRepoStatsUrl;
	



	public String getGitApiServiceRepoUrl() {
		return gitApiServiceRepoUrl;
	}

	public void setGitApiServiceRepoUrl(String gitApiServiceRepoUrl) {
		this.gitApiServiceRepoUrl = gitApiServiceRepoUrl;
	}

	public String getGitApiRepoStatsUrl() {
		return gitApiRepoStatsUrl;
	}

	public void setGitApiRepoStatsUrl(String gitApiRepoStatsUrl) {
		this.gitApiRepoStatsUrl = gitApiRepoStatsUrl;
	}

	
}
