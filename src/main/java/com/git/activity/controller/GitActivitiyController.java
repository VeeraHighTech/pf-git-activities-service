package com.git.activity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.git.activity.constants.GitConstants;
import com.git.activity.exceptionHandling.ErrorCode;
import com.git.activity.exceptionHandling.ErrorMessage;
import com.git.activity.exceptionHandling.ErrorMessageGenerator;
import com.git.activity.exceptionHandling.GitCustomException;
import com.git.activity.service.IGitActivityService;
import com.git.activity.utils.GitRequestVerificationUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GitActivitiyController {


	@Autowired
	IGitActivityService iGitService;

	@Autowired
	ErrorMessageGenerator errorMessageGenerator;

	@Autowired
	GitRequestVerificationUtil validationUtils;


	@ApiOperation(value = "Retrieves the statistics of the given Repository", notes = "getRepositories", response = String.class)
	@ApiResponses({
		@ApiResponse(code = 200, message = "Successfully retrived  repos", response = Object.class) })
	@RequestMapping(value = "/users/{user-name}/repos/{repo-name}", method = RequestMethod.POST,
	produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> populateDataStore(
			@ApiParam(value = "owner-name") @PathVariable(name = "user-name") String ownername,
			@ApiParam(value = "repo-name") @PathVariable(name = "repo-name") String serviceName,
            @ApiParam(value = "git-token") @RequestHeader(value = "git-token") String token)  {
         

		ResponseEntity<Object> responseEntity = null;

		try {
			validationUtils.verifyRepositoryRequest(ownername, serviceName);
			iGitService.poulateRepositoryStatistics(ownername,serviceName);
			new ResponseEntity<>(GitConstants.GIT_REPOS_SUCCESS, HttpStatus.OK);  

		} catch (GitCustomException gitCustomException) {
            ErrorMessage errorMessage = errorMessageGenerator.generateError(gitCustomException);
            responseEntity =  new ResponseEntity<>(errorMessage,
                    ErrorCode.httpStatusCode((ErrorCode) gitCustomException.getErrorCode()));
        } 

		return responseEntity;

	}
	
	
	@ApiOperation(value = "Retrieves the statistics of the given Repository", notes = "getRepositories", response = Object.class)
	@ApiResponses({
		@ApiResponse(code = 200, message = "Successfully retrived  repos", response = Object.class) })
	@RequestMapping(value = "/repositories/owner/{owner-name}/service/{service-name}", method = RequestMethod.GET,
	produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> getRepositoryWeeklyStatictics(
			@ApiParam(value = "owner-name") @PathVariable(name = "owner-name") String ownername,
			@ApiParam(value = "repo-name") @PathVariable(name = "service-name") String serviceName,
            @ApiParam(value = "git-token") @RequestHeader(value = "git-token") String token) {

		ResponseEntity<Object> responseEntity = null;

		try {
            
			validationUtils.verifyStatisticsRequest(ownername, serviceName);
			iGitService.collectRepositoryStatistics(serviceName, ownername);
			new ResponseEntity<>(GitConstants.GIT_RETRIEVES_SUCCESS, HttpStatus.OK); 

		}catch (GitCustomException gitCustomException) {
            ErrorMessage errorMessage = errorMessageGenerator.generateError(gitCustomException);
            responseEntity =  new ResponseEntity<>(errorMessage,
                    ErrorCode.httpStatusCode((ErrorCode) gitCustomException.getErrorCode()));
        } 

		return responseEntity;
	}




}
