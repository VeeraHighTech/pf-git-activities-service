package com.git.activities.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class WeeklyStatistics implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "week_of")
	private Date weekOf;
	
    @Column(name = "total_commits")
	private int totalCommits;
    
    
    @ManyToOne(optional=false)
    @JoinColumn(name="repoId",referencedColumnName="repo_id", insertable=false, updatable=false)
    private RepoDetails repo;
    
    
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "week_id", referencedColumnName = "id", nullable = false)
	public Set<DailyStatistics> dailyStatistics;
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getWeekOf() {
		return weekOf;
	}

	public void setWeekOf(Date weekOf) {
		this.weekOf = weekOf;
	}

	public int getTotalCommits() {
		return totalCommits;
	}

	public void setTotalCommits(int totalCommits) {
		this.totalCommits = totalCommits;
	}

	public RepoDetails getRepo() {
		return repo;
	}

	public void setRepo(RepoDetails repo) {
		this.repo = repo;
	}

	public Set<DailyStatistics> getDailyStatistics() {
		return dailyStatistics;
	}

	public void setDailyStatistics(Set<DailyStatistics> dailyStatistics) {
		this.dailyStatistics = dailyStatistics;
	}

    
}
