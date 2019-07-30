package com.git.activities.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.git.activities.service.IGitService;
import com.git.activities.utils.RestTemplateUtility;

@Service	
public class GitServiceImpl implements IGitService {


	@Autowired 
	RestTemplateUtility restTemplateUtility;
	

	@Override
	public void collectRepositoryStatistics(String serviceName, String ownerName) throws Exception{
		
		restTemplateUtility.getRepositoryDetailsByOwner(serviceName, ownerName);
		
	}
	
	
	
	
}
