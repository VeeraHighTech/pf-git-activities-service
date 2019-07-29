package com.git.activities.dto;

import java.util.Date;
import java.util.List;

public class CommitDetails {

	private Date weekOf;
	private int totalnumberOfCommits;
	List<DailyCommitsDetails> dcommits;

	public Date getWeekOf() {
		return weekOf;
	}

	public void setWeekOf(Date weekOf) {
		this.weekOf = weekOf;
	}

	public int getTotalnumberOfCommits() {
		return totalnumberOfCommits;
	}

	public void setTotalnumberOfCommits(int totalnumberOfCommits) {
		this.totalnumberOfCommits = totalnumberOfCommits;
	}

	public List<DailyCommitsDetails> getDcommits() {
		return dcommits;
	}

	public void setDcommits(List<DailyCommitsDetails> dcommits) {
		this.dcommits = dcommits;
	}

	@Override
	public String toString() {
		return "CommitDetails [weekOf=" + weekOf + ", totalnumberOfCommits=" + totalnumberOfCommits + ", dcommits="
				+ dcommits + "]";
	}

}
