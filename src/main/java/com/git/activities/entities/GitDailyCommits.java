package com.git.activities.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "git_daily_commits", schema = "git_sql_db")
public class GitDailyCommits implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "repo_name", nullable = false, unique = true)
    private String repoName;
    
    @Column(name = "week_of", nullable = false)
	private Date week_of;
	
    @Column(name = "commits")
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
    
    
}
