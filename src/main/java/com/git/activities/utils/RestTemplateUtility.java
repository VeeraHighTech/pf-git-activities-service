package com.git.activities.utils;

import static java.text.MessageFormat.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.git.activities.application.GitApiServiceConfig;
import com.git.activities.entities.DailyStatistics;
import com.git.activities.entities.RepoDetails;
import com.git.activities.entities.WeeklyStatistics;
import com.git.activities.repo.ReposRepository;

@Component
public class RestTemplateUtility {

	@Autowired
	RestTemplate serviceRestTemplate;

	@Autowired
	GitApiServiceConfig gitApiServiceConfig;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	DataStoreUtility dataStoreUtility;
	
	@Autowired
	DateConvertionUtility dateConvertionUtility;

	@Autowired
	ReposRepository reposRepository;

	
	
	@SuppressWarnings("unchecked")
	public void getRepositoryDetailsByOwner(String serviceName, String ownerName) {

		UriComponentsBuilder builders = UriComponentsBuilder.fromUriString(format(gitApiServiceConfig.getGitApiServiceRepoUrl(), ownerName));
		String uriBuilder = builders.build().encode().toUriString();

		ResponseEntity<List> rateResponse = null;
		List<LinkedHashMap> repoResponseList = null;
		List<RepoDetails> repoDetailsList = new ArrayList<>();
		RepoDetails repoDetails = null;
		Set<WeeklyStatistics> weeklyStatisticsList =null;

		try {
			
			rateResponse = serviceRestTemplate.getForEntity(uriBuilder, List.class);
			repoResponseList = rateResponse.getBody();

			for (LinkedHashMap obj : repoResponseList) {
				LinkedHashMap gitRepoActResponse = (LinkedHashMap) obj;
				repoDetails = new RepoDetails((Integer) gitRepoActResponse.get("id"),(String) gitRepoActResponse.get("name"),(String) gitRepoActResponse.get("created_at"),(String) gitRepoActResponse.get("updated_at"),ownerName);
			
				weeklyStatisticsList = populateCommitsByRepository( (String)gitRepoActResponse.get("name"),  ownerName);
				repoDetails.setWeeklyStatistics(weeklyStatisticsList);
				reposRepository.save(repoDetails);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public Set<WeeklyStatistics>  populateCommitsByRepository(String serviceName, String ownerName) {
		

		ResponseEntity<List> rateResponse = null;
		List<LinkedHashMap> repoResponseList = null;
		WeeklyStatistics weeklyStatistics = null;
		Set<WeeklyStatistics> weeklyStatisticsList = new HashSet<>();
    	DailyStatistics dailyStatistics =null;
    	Set<DailyStatistics> dailyStatisticsList = new HashSet<>();
    	ArrayList days= new ArrayList();
    	Date nextDate = null;
    	Date weekOf=null;
		
		UriComponentsBuilder builders = UriComponentsBuilder.fromUriString(format(gitApiServiceConfig.getGitApiRepoStatsUrl(), ownerName,serviceName));
        String uriBuilder = builders.build().encode().toUriString();
		
        try {
        	rateResponse = serviceRestTemplate.getForEntity(uriBuilder, List.class);
        	repoResponseList = rateResponse.getBody();
        	
        	
        	
        	for (LinkedHashMap obj : repoResponseList) {
        		weeklyStatistics = new WeeklyStatistics();
				LinkedHashMap gitRepoActResponse = (LinkedHashMap) obj;
				
				Integer total = (Integer) gitRepoActResponse.get("total");
				Integer week =  (Integer) gitRepoActResponse.get("week");
				days =  (ArrayList) gitRepoActResponse.get("days");
				
				weekOf = dateConvertionUtility.getWeekDateFromMilliSec(week);
				
				weeklyStatistics.setTotalCommits(total);
				weeklyStatistics.setWeekOf(weekOf);
				weeklyStatisticsList.add(weeklyStatistics);
				
				
				for(int i =0; i<days.size(); i++) {
				dailyStatistics = new DailyStatistics();
				dailyStatistics.setDay(weekOf);
				Integer commits = (int) days.get(i);
				dailyStatistics.setTotalCommits(commits);
				dailyStatisticsList.add(dailyStatistics);
				nextDate= getNextDate(weekOf);
				weekOf=nextDate;
				
			}
			weeklyStatistics.setDailyStatistics(dailyStatisticsList);
				 
        	}
        	  
        	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		
			
		return weeklyStatisticsList;
	}
	
 public static Date getNextDate(Date weekOf) throws ParseException {
		
	    String currentDate = null;
	    String nextDate = null;
	    LocalDate da= new LocalDate(weekOf);
	    currentDate = da.toString();
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(currentDate));
		c.add(Calendar.DATE, 1); // number of days to add
		nextDate = sdf.format(c.getTime());
		Date date = (Date)sdf.parse(nextDate);
	    
		return date;
		
	}

}
