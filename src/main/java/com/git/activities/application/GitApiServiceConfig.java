package com.git.activities.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GitApiServiceConfig {

	@Value("${git.api.service.repo.url}")
	private String gitApiServiceRepoUrl;

	@Value("${git.api.service.username}")
	private String gitApiServiceUserName;

	@Value("${git.api.service.password}")
	private String gitApiServicePassword;
	
	@Value("${git.api.repo.commit.url}")
	private String gitApiCommitUrl;
	


	public String getGitApiServiceRepoUrl() {
		return gitApiServiceRepoUrl;
	}

	public void setGitApiServiceRepoUrl(String gitApiServiceRepoUrl) {
		this.gitApiServiceRepoUrl = gitApiServiceRepoUrl;
	}

	public String getGitApiCommitUrl() {
		return gitApiCommitUrl;
	}

	public void setGitApiCommitUrl(String gitApiCommitUrl) {
		this.gitApiCommitUrl = gitApiCommitUrl;
	}

	public String getGitApiServiceUserName() {
		return gitApiServiceUserName;
	}

	public void setGitApiServiceUserName(String gitApiServiceUserName) {
		this.gitApiServiceUserName = gitApiServiceUserName;
	}

	public String getGitApiServicePassword() {
		return gitApiServicePassword;
	}

	public void setGitApiServicePassword(String gitApiServicePassword) {
		this.gitApiServicePassword = gitApiServicePassword;
	}

}
