package com.git.activities.dto;

import java.util.Arrays;

public class CommitsResponse {

	
	private Long total;
	private Long week;
	private int days[]=new int[7];
	
	
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getWeek() {
		return week;
	}
	public void setWeek(Long week) {
		this.week = week;
	}
	public int[] getDays() {
		return days;
	}
	public void setDays(int[] days) {
		this.days = days;
	}
	@Override
	public String toString() {
		return " [total=" + total + ", week=" + week + ", days=" + Arrays.toString(days) + "]";
	}
}
