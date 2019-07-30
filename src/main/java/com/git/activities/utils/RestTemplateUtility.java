package com.git.activities.utils;

import static java.text.MessageFormat.format;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.git.activities.application.GitApiServiceConfig;
import com.git.activities.dto.CommitsResponse;
import com.git.activities.dto.GitRepositoriesResponse;
import com.git.activities.entities.RepoDetails;
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
	ReposRepository reposRepository;

	public List<GitRepositoriesResponse> getRepositoryDetailsByOwner(String serviceName, String ownerName) {

		UriComponentsBuilder builders = UriComponentsBuilder.fromUriString(format(gitApiServiceConfig.getGitApiServiceRepoUrl(), ownerName));
        String uriBuilder = builders.build().encode().toUriString();
		List<GitRepositoriesResponse> listOfrepos = new ArrayList<>();

		ResponseEntity<Object[]> repos = serviceRestTemplate.getForEntity(uriBuilder, Object[].class);

		try {
			String repoResponse = objectMapper.writeValueAsString(repos);
			listOfrepos = objectMapper.readValue(repoResponse, new TypeReference<List<GitRepositoriesResponse>>() {
			});
			dataStoreUtility.saveOrUpdateRepos(listOfrepos);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listOfrepos;
	}

	@SuppressWarnings("unchecked")
	public List<GitRepositoriesResponse> getCommitsFromSrvice(String serviceName, String ownerName) {
		// "https://api.github.com/repos/VeeraHighTech/vrk-mongodb-springboot-springdata/stats/commit_activity",

		List<CommitsResponse> commitrResponse = null;
		List<GitRepositoriesResponse> gitlist = new ArrayList<>();
		GitRepositoriesResponse repos = new GitRepositoriesResponse();
		RepoDetails repository = null;

		String url = gitApiServiceConfig.getGitApiServiceRepoUrl() + "/repos/" + ownerName + "/" + serviceName
				+ "/stats/commit_activity";

		List<CommitsResponse> reposCommits = serviceRestTemplate.getForObject(url, List.class);
		try {
			String gitCommitString = objectMapper.writeValueAsString(reposCommits);
			commitrResponse = objectMapper.readValue(gitCommitString, new TypeReference<List<CommitsResponse>>() {
			});
			repos.setGitdailycommits(commitrResponse);
			repos.setName(serviceName);
			gitlist.add(repos);

			repository = reposRepository.findByRepoName(serviceName);
			if (reposRepository != null) {
				repos.setId(repository.getId());
				repos.setCreated_at(repository.getCreatedAt());
				repos.setUpdated_at(repository.getUpdatedAt());
				gitlist.add(repos);
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return gitlist;
	}

}
