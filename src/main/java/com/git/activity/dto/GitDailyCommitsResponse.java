package com.git.activity.dto;

import java.util.Date;

public class GitDailyCommitsResponse{


    private Long id;
    
    private String repoName;
    
	private Date week_of;
   
	private Long commits;

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

	public Date getWeek_of() {
		return week_of;
	}

	public void setWeek_of(Date week_of) {
		this.week_of = week_of;
	}

	public Long getCommits() {
		return commits;
	}

	public void setCommits(Long commits) {
		this.commits = commits;
	}

	@Override
	public String toString() {
		return "GitDailyCommitsResponse [id=" + id + ", repoName=" + repoName + ", week_of=" + week_of + ", commits="
				+ commits + "]";
	}
    
}
