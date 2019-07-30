package com.git.activities.entities;

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
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "day")
	private Date day;
	
    @Column(name = "total_commits")
	private Integer totalCommits;
    
    
    @ManyToOne(optional=false)
    @JoinColumn(name="week_id",referencedColumnName="id", insertable=false, updatable=false)
    private WeeklyStatistics week;
    
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public WeeklyStatistics getWeek() {
		return week;
	}

	public void setWeek(WeeklyStatistics week) {
		this.week = week;
	}
    
}
