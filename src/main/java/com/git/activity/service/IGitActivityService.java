package com.git.activity.service;

import java.util.List;

import com.git.activity.entities.WeeklyStatistics;
import com.git.activity.exceptionHandling.GitCustomException;

public interface IGitActivityService {

	public void  poulateRepositoryStatistics(String userName, String repoName) throws GitCustomException;

	public List<WeeklyStatistics> collectRepositoryStatistics(String userName, String repoName) throws GitCustomException;
	
}
