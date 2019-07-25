package com.git.activities.service;

import org.springframework.http.ResponseEntity;

public interface IGitService {

	public ResponseEntity<Object> getRepositories(); 
	
	public ResponseEntity<Object> getCommitsFromService(String ServiceName);
}
