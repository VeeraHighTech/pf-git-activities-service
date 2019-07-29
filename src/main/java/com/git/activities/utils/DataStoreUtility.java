package com.git.activities.utils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.git.activities.dto.CommitDetails;
import com.git.activities.dto.CommitsResponse;
import com.git.activities.dto.GitRepositoriesResponse;
import com.git.activities.entities.RepoWeeklyStatistics;
import com.git.activities.entities.Repositories;
import com.git.activities.repo.ReposRepository;
import com.git.activities.repo.WeeklyCommitsRepository;

@Component
public class DataStoreUtility {

	@Autowired
	ReposRepository reposRepository;

	@Autowired
	WeeklyCommitsRepository weeklyCommitsRepository;

	@Autowired
	DateConvertionUtility dateConvertionUtility;

	public void saveOrUpdateRepos(List<GitRepositoriesResponse> listOfrepos) {

		List<Repositories> listofRepos = new ArrayList<>();
		Repositories repos = null;

		for (GitRepositoriesResponse grep : listOfrepos) {
			repos = new Repositories();
			repos.setId(grep.getId());
			repos.setRepoName(grep.getName());
			repos.setCreatedAt(grep.getCreated_at());
			repos.setUpdatedAt(grep.getUpdated_at());
			listofRepos.add(repos);
		}
		if (listofRepos != null) {
			reposRepository.deleteAll();
			reposRepository.saveAll(listofRepos);
		}

	}

	public void saveOrUpdateWeeklyCommits(List<CommitsResponse> gitcommitsList, Repositories repository) {

		List<RepoWeeklyStatistics> listofWeeklyRepos = new ArrayList<>();
		RepoWeeklyStatistics weeklystats = null;
		List<CommitDetails> cdList = new ArrayList<>();
		CommitDetails commitDetails = null;

		for (CommitsResponse cr : gitcommitsList) {
			commitDetails = new CommitDetails();
			commitDetails.setTotalnumberOfCommits(cr.getTotal());
			Date weekOf = dateConvertionUtility.getWeekDateFromMilliSec(cr.getWeek());
			commitDetails.setWeekOf(weekOf);
			cdList.add(commitDetails);
		}

		for (CommitDetails cDetails : cdList) {
			weeklystats = new RepoWeeklyStatistics();
			weeklystats.setTotalCommits(cDetails.getTotalnumberOfCommits());
			weeklystats.setWeekOf(cDetails.getWeekOf());
			listofWeeklyRepos.add(weeklystats);
		}

		if (listofWeeklyRepos != null) {
			weeklyCommitsRepository.deleteAll();
			weeklyCommitsRepository.saveAll(listofWeeklyRepos);
		}

	}

	public void saveOrUpdateDailyCommits(List<GitRepositoriesResponse> listOfrepos) {

	}
}