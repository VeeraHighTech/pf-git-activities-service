package com.git.activity.dto;

import java.util.Date;

public class DailyCommitsDetails {

	private Date day;
	private int numberOfCommits;
	
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	public int getNumberOfCommits() {
		return numberOfCommits;
	}
	public void setNumberOfCommits(int numberOfCommits) {
		this.numberOfCommits = numberOfCommits;
	}
	@Override
	public String toString() {
		return "DailyCommitsDetails [day=" + day + ", numberOfCommits=" + numberOfCommits + "]";
	}
	
}
