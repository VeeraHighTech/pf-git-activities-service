package com.git.activity.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class DailyStatistics implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "day")
	private Date day;
	
    @Column(name = "total_commits")
	private Integer totalCommits;
    
    
    @ManyToOne(optional=false)
    @JoinColumn(name="weekId",referencedColumnName="week_id", insertable=false, updatable=false)
    private WeeklyStatistics weeklyStaistics;
    
	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public Integer getTotalCommits() {
		return totalCommits;
	}

	public void setTotalCommits(Integer totalCommits) {
		this.totalCommits = totalCommits;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public WeeklyStatistics getWeeklyStaistics() {
		return weeklyStaistics;
	}

	public void setWeeklyStaistics(WeeklyStatistics weeklyStaistics) {
		this.weeklyStaistics = weeklyStaistics;
	}

	
    
}
