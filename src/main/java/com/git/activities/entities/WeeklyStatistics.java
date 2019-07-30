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
public class WeeklyStatistics implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "repo_id", nullable = false, unique = true)
    private Long repoId;
    
    @Column(name = "week_of", nullable = false)
	private Date weekOf;
	
    @Column(name = "total_commits")
	private Long totalCommits;
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRepoId() {
		return repoId;
	}

	public void setRepoId(Long repoId) {
		this.repoId = repoId;
	}

	public Date getWeekOf() {
		return weekOf;
	}

	public void setWeekOf(Date weekOf) {
		this.weekOf = weekOf;
	}

	public Long getTotalCommits() {
		return totalCommits;
	}

	public void setTotalCommits(Long totalCommits) {
		this.totalCommits = totalCommits;
	}
    
    
}
