package com.git.activities.dto;

import java.util.Arrays;

public class CommitsResponse {

	
	private int total;
	private long week;
	private int days[]=new int[7];
	
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public long getWeek() {
		return week;
	}
	public void setWeek(long week) {
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
