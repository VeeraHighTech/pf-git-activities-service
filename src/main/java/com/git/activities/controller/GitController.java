package com.git.activities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.git.activities.service.IGitService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GitController {
	
	// private static final Logger logger = LoggerFactory.getLogger(GitController.class);

	@Autowired
	IGitService iGitService;
	
	@ApiOperation(value = "Get Repositories", notes = "getRepositories", response = String.class)
	@ApiResponses({
			@ApiResponse(code = 200, message = "Successfully retrived  repos", response = String.class) })
	@RequestMapping(value = "/getRepositories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> getRepositories() {

		ResponseEntity<Object> responseEntity = null;

		try {

			responseEntity = iGitService.getRepositories();

			if (responseEntity != null) {
				responseEntity = new ResponseEntity<>(responseEntity, HttpStatus.OK);
			} else {
				responseEntity = new ResponseEntity<>(responseEntity, HttpStatus.NOT_FOUND);
			}

		} catch (Exception ex) {
           ex.printStackTrace();
		}

		return responseEntity;
	}

	@ApiOperation(value = "Get Repositories", notes = "getRepositories", response = String.class)
	@ApiResponses({
			@ApiResponse(code = 200, message = "Successfully retrived  repos", response = String.class) })
	@RequestMapping(value = "/repositories/service/{service-name}/commits", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> getCommitsFromService(
			@ApiParam(value = "service-name") @PathVariable(name = "service-name", required = true) String serviceName){
	
		ResponseEntity<Object> responseEntity = null;

		try {

			responseEntity = iGitService.getCommitsFromService(serviceName);

			if (responseEntity != null) {
				responseEntity = new ResponseEntity<>(responseEntity, HttpStatus.OK);
			} else {
				responseEntity = new ResponseEntity<>(responseEntity, HttpStatus.NOT_FOUND);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return responseEntity;
	}
	
	
	
}
