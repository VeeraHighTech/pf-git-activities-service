package com.git.activity.external;

import static java.text.MessageFormat.format;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.git.activity.config.GitApiServiceConfig;
import com.git.activity.entities.DailyStatistics;
import com.git.activity.entities.RepoDetails;
import com.git.activity.entities.WeeklyStatistics;
import com.git.activity.exceptionHandling.ErrorCode;
import com.git.activity.exceptionHandling.GitCustomException;
import com.git.activity.repo.ReposRepository;
import com.git.activity.utils.DateUtils;

@Component
public class GitAPIService {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	GitApiServiceConfig gitApiServiceConfig;

	@Autowired
	ObjectMapper objectMapper;


	@Autowired
	DateUtils dateConvertionUtility;

	@Autowired
	ReposRepository reposRepository;

	private static final Logger logger = LoggerFactory.getLogger(GitAPIService.class);


	@SuppressWarnings("unchecked")
	public void processRespositoryInfo(String userName, String repoName) throws GitCustomException {

		ResponseEntity<List> rateResponse = null;
		List<LinkedHashMap> repoResponseList = null;
		RepoDetails repoDetails = null;
		Set<WeeklyStatistics> weeklyStatisticsList =null;
		String gitAPIURL = null;
		try {

			/*HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "token d463a7897af1d6bc328364a8c9e5a49e5f86eb50 "); 
			headers.add("Accept", "application/json");
			headers.setContentType(MediaType.APPLICATION_JSON);*/
			//HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);


			gitAPIURL = getRepoURL(userName,repoName, false);

			rateResponse = restTemplate.getForEntity(gitAPIURL, List.class);

			repoResponseList = rateResponse.getBody();
			logger.info("Successfully collected the Repository details");
			for(LinkedHashMap obj : repoResponseList) { 
				LinkedHashMap gitRepoActResponse = (LinkedHashMap) obj; 
				repoDetails = new RepoDetails((Integer)
						gitRepoActResponse.get("id"),(String) gitRepoActResponse.get("name"),(String)
						gitRepoActResponse.get("created_at"),(String)
						gitRepoActResponse.get("updated_at"),userName);

				weeklyStatisticsList = populateCommitsByRepository(
						(String)gitRepoActResponse.get("name"), userName);
				repoDetails.setWeeklyStatistics(weeklyStatisticsList);
				reposRepository.save(repoDetails); }

		}  catch (HttpClientErrorException hCEEx) {

			if(hCEEx.getRawStatusCode() == HttpStatus.NOT_FOUND.value()) {
				logger.error(format("The server has not found anything matching the Request-URI {0}",gitAPIURL));
				throw new GitCustomException(ErrorCode.GIT_404, new Object[]{gitAPIURL}); 

			}
			logger.error(format("Problem occured while collecting the respositiry information and the error is {0}",hCEEx.getMessage()));
			throw new GitCustomException(ErrorCode.GIT_405, new Object[]{hCEEx.getMessage()});
		}


		catch (Exception e) {
			logger.error("Problem occured while collecting the respositiry information and the error is {}",e);
			 throw new GitCustomException(ErrorCode.GIT_451, new Object[]{e});
		}

	}

	@SuppressWarnings("unchecked")
	public Set<WeeklyStatistics>  populateCommitsByRepository(String repoName, String ownerName) throws GitCustomException {


		ResponseEntity<List> rateResponse = null;
		List<LinkedHashMap> repoResponseList = null;
		WeeklyStatistics weeklyStatistics = null;
		Set<WeeklyStatistics> weeklyStatisticsList = new HashSet<>();



		try {
			rateResponse = restTemplate.getForEntity(getRepoURL(ownerName,repoName, true), List.class);
			repoResponseList = rateResponse.getBody();

			for (LinkedHashMap obj : repoResponseList) {
				weeklyStatistics = new WeeklyStatistics();
				LinkedHashMap gitRepoActResponse = (LinkedHashMap) obj;

				Integer total = (Integer) gitRepoActResponse.get("total");
				Integer week =  (Integer) gitRepoActResponse.get("week");
				List <Integer> days =  (ArrayList<Integer>) gitRepoActResponse.get("days");

				Date weekOf = dateConvertionUtility.getWeekDateFromMilliSec(week);

				weeklyStatistics.setTotalCommits(total);
				weeklyStatistics.setWeekOf(weekOf);
				weeklyStatisticsList.add(weeklyStatistics);
				weeklyStatistics.setDailyStatistics(computeDailyStatistics(days, weekOf));

			}


		} catch (Exception e) {
			logger.error("Problem occured while collecting the respositiry information and the error is {}",e);
			 throw new GitCustomException(ErrorCode.GIT_451, new Object[]{e});
		}


		return weeklyStatisticsList;
	}




	private String getRepoURL(String ownerName, String repoName, boolean isRepoWeeklyStats) throws GitCustomException{

		UriComponentsBuilder uriBuilder = null;
		try {

			if(!Strings.isBlank(repoName) && !Strings.isBlank(ownerName) && !isRepoWeeklyStats) {
				uriBuilder =  UriComponentsBuilder.fromUriString(format(gitApiServiceConfig.getGitRepoURL(), ownerName,repoName));
			}else if(Strings.isBlank(repoName) && !Strings.isBlank(ownerName)){
				uriBuilder =  UriComponentsBuilder.fromUriString(format(gitApiServiceConfig.getGitRepos(), ownerName));
			}else if (!Strings.isBlank(repoName) && !Strings.isBlank(ownerName) && isRepoWeeklyStats){
				uriBuilder = UriComponentsBuilder.fromUriString(format(gitApiServiceConfig.getGitRepoStats(), ownerName,repoName));
			}else {
				logger.error("Invalid Option");
				throw new GitCustomException(ErrorCode.GIT_003, new Object[]{}); 
			}


		}catch(Exception ex) {
			logger.error("Problem occured while building the git repo uri");
			throw new GitCustomException(ErrorCode.GIT_004, new Object[]{}); 
		}

		return   uriBuilder.build().encode().toUriString();


	}


	private Set<DailyStatistics> computeDailyStatistics(List<Integer> days, Date presentDay) throws Exception {

		Set<DailyStatistics> dailyStatsPerWeek= new HashSet<>();
		DailyStatistics dailyStat = null;
		for(Integer commitCount : days) {
			dailyStat = new DailyStatistics();
			dailyStat.setDay(presentDay);
			dailyStat.setTotalCommits(days.get(commitCount));
			dailyStatsPerWeek.add(dailyStat);
			presentDay=dateConvertionUtility.getNextDayForTheGivenDate(presentDay);;

		}

		return dailyStatsPerWeek;
	}

}
