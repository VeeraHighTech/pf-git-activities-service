package com.git.activities.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.git.activities.application.GitApiServiceConfig;
import com.git.activities.service.IGitService;

@Service	
public class GitServiceImpl implements IGitService {

	@Autowired
	RestTemplate serviceTemplate;

	@Autowired
	GitApiServiceConfig gitApiServiceConfig;

	@Override
	public ResponseEntity<Object> getRepositories() {

		ResponseEntity<Object> response = null;

		ResponseEntity<List<Object>> restResponse = serviceTemplate.exchange(
				gitApiServiceConfig.getGitApiServiceRepoUrl(), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Object>>() {
				});

		List<Object> repolist = restResponse.getBody();
		response = new ResponseEntity<>(repolist, HttpStatus.OK);
		return response;
	}

	@Override
	public ResponseEntity<Object> getCommitsFromService(String serviceName) {
		ResponseEntity<Object> response = null;
		String url = gitApiServiceConfig.getGitApiCommitUrl() + "/" + serviceName + "/commits";

		ResponseEntity<List<Object>> restResponse = serviceTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Object>>() {
				});

		List<Object> repolist = restResponse.getBody();
		response = new ResponseEntity<>(repolist, HttpStatus.OK);
		return response;
	}
	
	
	
	
	
	
	

}
