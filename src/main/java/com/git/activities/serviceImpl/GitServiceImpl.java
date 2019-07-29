package com.git.activities.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.git.activities.dto.GitRepositoriesResponse;
import com.git.activities.service.IGitService;
import com.git.activities.utils.RestTemplateUtility;
import com.google.common.base.Strings;

@Service	
public class GitServiceImpl implements IGitService {


	@Autowired 
	RestTemplateUtility restTemplateUtility;
	

	@Override
	public List<GitRepositoriesResponse> getRepositoriesAndCommits(String serviceName, String ownerName) {
		
		List<GitRepositoriesResponse> gitRepositoriesResponse = null;
		
		if (Strings.isNullOrEmpty(serviceName) && !Strings.isNullOrEmpty(ownerName)) {
			gitRepositoriesResponse= getRepos(serviceName,ownerName);
		}
		
		if (!Strings.isNullOrEmpty(serviceName) && !Strings.isNullOrEmpty(ownerName)) {
			gitRepositoriesResponse=getCommitsFromSrvice( serviceName, ownerName);
		}
		
		return gitRepositoriesResponse;
	}
	
	
	public List<GitRepositoriesResponse> getRepos(String serviceName, String ownerName) {
		
		List<GitRepositoriesResponse> listOfrepos = null;
		
		listOfrepos = restTemplateUtility.getRepos(serviceName, ownerName);
		
		return listOfrepos;
	}
	
	
	public List<GitRepositoriesResponse> getCommitsFromSrvice(String serviceName, String ownerName) {
		
		List<GitRepositoriesResponse> gitlist= null;
		
		gitlist= restTemplateUtility.getCommitsFromSrvice(serviceName, ownerName);
		
		return gitlist;
	}
	
	
	
}
