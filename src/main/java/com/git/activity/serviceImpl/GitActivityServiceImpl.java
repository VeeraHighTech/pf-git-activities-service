package com.git.activity.serviceImpl;

import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.git.activity.entities.WeeklyStatistics;
import com.git.activity.exceptionHandling.GitCustomException;
import com.git.activity.external.GitAPIService;
import com.git.activity.repo.ReposRepository;
import com.git.activity.repo.WeeklyCommitsRepository;
import com.git.activity.service.IGitActivityService;
import com.git.activity.exceptionHandling.ErrorCode;

@Service	
public class GitActivityServiceImpl implements IGitActivityService {

	private static final Logger logger = LoggerFactory.getLogger(GitActivityServiceImpl.class);

	@Autowired 
	GitAPIService gitAPIService;
	
	@Autowired
	ReposRepository repoRepository;
	
	@Autowired
	WeeklyCommitsRepository weeklyRepository;
	

	@Override
	public void poulateRepositoryStatistics(String userName, String repoName) throws GitCustomException{

		gitAPIService.processRespositoryInfo(userName, repoName);
		
	}


	@Override
	public List<WeeklyStatistics> collectRepositoryStatistics(String userName, String repoName) throws GitCustomException {
		
		if(!Strings.isBlank(userName) && !Strings.isBlank(repoName)) {
		//	return repoRepository.findByOwnerNameAndName(userName, repoName);
		}else if(!Strings.isBlank(userName) && Strings.isBlank(repoName)) {
			return  weeklyRepository.findByRepo(userName);
			//return repoRepository.findByOwnerName(userName);
		}else {
			 logger.error("Unable to find Repos for given userName {} repoName {} ", userName, repoName);
			 throw new GitCustomException(ErrorCode.GIT_400, new Object[]{}); 
			
		}
		
		return null;
		
	}
	
	
	
	
}
