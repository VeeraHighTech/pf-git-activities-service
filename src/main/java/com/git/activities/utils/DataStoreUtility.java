package com.git.activities.utils;

import org.springframework.stereotype.Component;

@Component
public class DataStoreUtility {

	/*@Autowired
	ReposRepository reposRepository;

	@Autowired
	WeeklyCommitsRepository weeklyCommitsRepository;

	@Autowired
	DateConvertionUtility dateConvertionUtility;*/

	/*public void saveOrUpdateRepos(List<GitRepositoriesResponse> listOfrepos) {

		List<RepoDetails> listofRepos = new ArrayList<>();
		RepoDetails repos = null;

		for (GitRepositoriesResponse grep : listOfrepos) {
			repos = new RepoDetails();
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

	}*/

	/*public void saveOrUpdateWeeklyCommits(List<CommitsResponse> gitcommitsList, RepoDetails repository) {

		List<WeeklyStatistics> listofWeeklyRepos = new ArrayList<>();
		WeeklyStatistics weeklystats = null;
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
			weeklystats = new WeeklyStatistics();
			weeklystats.setTotalCommits(cDetails.getTotalnumberOfCommits());
			weeklystats.setWeekOf(cDetails.getWeekOf());
			listofWeeklyRepos.add(weeklystats);
		}

		if (listofWeeklyRepos != null) {
			weeklyCommitsRepository.deleteAll();
			weeklyCommitsRepository.saveAll(listofWeeklyRepos);
		}*/

	/*}

	public void saveOrUpdateDailyCommits(List<GitRepositoriesResponse> listOfrepos) {

	}*/
}