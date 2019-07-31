package com.git.activity.utils;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import com.git.activity.exceptionHandling.ErrorCode;
import com.git.activity.exceptionHandling.GitCustomException;

@Component
public class GitRequestVerificationUtil {

	
	public void verifyRepositoryRequest(String ownerName, String serviceName) throws GitCustomException {
		
		 if(serviceName.equalsIgnoreCase("undefined")) {
			 serviceName =null;
		 }
		
		 if(ownerName.equalsIgnoreCase("undefined") || Strings.isBlank(ownerName) ) {
			 throw new GitCustomException(ErrorCode.GIT_001, new Object[] {});
		 }

  	}
	
	public void verifyStatisticsRequest(String ownerName, String serviceName) throws GitCustomException {
		
		 if(serviceName.equalsIgnoreCase("undefined")) {
			 serviceName =null;
		 }
		
		 if(ownerName.equalsIgnoreCase("undefined") || 
				 serviceName.equalsIgnoreCase("undefined") || 
				 Strings.isBlank(ownerName) || Strings.isBlank(serviceName)) {
			 throw new GitCustomException(ErrorCode.GIT_002, new Object[] {});
		 }

 	}
	
	
	
}
