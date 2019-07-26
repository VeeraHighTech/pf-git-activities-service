package com.git.activities.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.git.activities.application.GitApiServiceConfig;
import com.git.activities.dto.CommitsResponse;
import com.git.activities.dto.GitDailyCommitsResponse;
import com.git.activities.dto.GitRepositoriesResponse;
import com.git.activities.service.IGitService;
import com.google.common.base.Strings;

@Service	
public class GitServiceImpl implements IGitService {

	@Autowired
	RestTemplate serviceTemplate;

	@Autowired
	GitApiServiceConfig gitApiServiceConfig;
	
	@Autowired
    ObjectMapper objectMapper;

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
		// "repos_url": "https://api.github.com/users/VeeraHighTech/repos",

		String url = gitApiServiceConfig.getGitApiServiceRepoUrl() + "/users/" + ownerName + "/repos";
		List<GitRepositoriesResponse> listOfrepos = new ArrayList<>();

		@SuppressWarnings("unchecked")
		List<LinkedHashMap<String, String>> repos = serviceTemplate.getForObject(url, List.class);
		
		for (LinkedHashMap<String, String> repo : repos) {
			GitRepositoriesResponse grp = new GitRepositoriesResponse();

			grp.setRepoName(repo.get("name"));
			listOfrepos.add(grp);
		}

		return listOfrepos;
	}
	
	
	public List<GitRepositoriesResponse> getCommitsFromSrvice(String serviceName, String ownerName) {
		// "https://api.github.com/repos/VeeraHighTech/vrk-mongodb-springboot-springdata/stats/commit_activity",
		
		List<CommitsResponse> commitrResponse = null;
		List<GitRepositoriesResponse> gitlist= new ArrayList<>();
		

		String url = gitApiServiceConfig.getGitApiServiceRepoUrl() + "/repos/" + ownerName + "/" + serviceName
				+ "/stats/commit_activity";
		
		@SuppressWarnings("unchecked")
		List<CommitsResponse> repos = serviceTemplate.getForObject(url, List.class);
		

		try {
			String gitCommitString = objectMapper.writeValueAsString(repos);
			commitrResponse = objectMapper.readValue(gitCommitString, new TypeReference<List<CommitsResponse>>() {});
			
			for(CommitsResponse cm :commitrResponse) {
				System.out.println(cm.getTotal());
				System.out.println(cm.getDays());
				System.out.println(cm.getWeek());
			}
	 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return gitlist;
	}
	
	
	
}
