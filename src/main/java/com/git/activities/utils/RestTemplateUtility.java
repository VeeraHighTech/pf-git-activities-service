package com.git.activities.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.git.activities.application.GitApiServiceConfig;
import com.git.activities.dto.CommitsResponse;
import com.git.activities.dto.GitRepositoriesResponse;

@Component
public class RestTemplateUtility {

	@Autowired
	RestTemplate serviceTemplate;
	
	@Autowired
	GitApiServiceConfig gitApiServiceConfig;
	
	@Autowired
    ObjectMapper objectMapper;
	
	@SuppressWarnings("unchecked")
	public List<GitRepositoriesResponse> getRepos(String serviceName, String ownerName) {
		// "repos_url": "https://api.github.com/users/VeeraHighTech/repos",

		String url = gitApiServiceConfig.getGitApiServiceRepoUrl() + "/users/" + ownerName + "/repos";
		List<GitRepositoriesResponse> listOfrepos = new ArrayList<>();

		
		List<GitRepositoriesResponse> repos = serviceTemplate.getForObject(url, List.class);
		
		try {
			String repoResponse= objectMapper.writeValueAsString(repos);
			listOfrepos = objectMapper.readValue(repoResponse, new TypeReference<List<GitRepositoriesResponse>>() {});
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return listOfrepos;
	}
	
	@SuppressWarnings("unchecked")
	public List<GitRepositoriesResponse> getCommitsFromSrvice(String serviceName, String ownerName) {
		// "https://api.github.com/repos/VeeraHighTech/vrk-mongodb-springboot-springdata/stats/commit_activity",
		
		List<CommitsResponse> commitrResponse = null;
		List<GitRepositoriesResponse> gitlist= new ArrayList<>();
		GitRepositoriesResponse repos= new GitRepositoriesResponse();

		String url = gitApiServiceConfig.getGitApiServiceRepoUrl() + "/repos/" + ownerName + "/" + serviceName
				+ "/stats/commit_activity";
		
		
		List<CommitsResponse> reposCommits = serviceTemplate.getForObject(url, List.class);
		try {
			String gitCommitString = objectMapper.writeValueAsString(reposCommits);
			commitrResponse = objectMapper.readValue(gitCommitString, new TypeReference<List<CommitsResponse>>() {});
			repos.setGitdailycommits(commitrResponse);
			repos.setName(serviceName);
			gitlist.add(repos); 
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return gitlist;
	}
	
	
}
