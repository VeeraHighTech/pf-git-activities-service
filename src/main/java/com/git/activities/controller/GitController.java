package com.git.activities.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
			@ApiResponse(code = 200, message = "Successfully retrived  repos", response = Object.class) })
	@RequestMapping(value = "/repositories/owner/{owner-name}/service/{service-name}", method = RequestMethod.POST,
	produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> populateDataStore(
			@ApiParam(value = "owner-name") @PathVariable(name = "owner-name") String ownername,
			@ApiParam(value = "service-name") @PathVariable(name = "service-name") String serviceName) {

		// do proper validation
		
		ResponseEntity<Object> responseEntity = null;

		
		try {

			 iGitService.collectRepositoryStatistics(serviceName, ownername);

			

		} catch (Exception ex) {
			ex.printStackTrace();
          System.out.println();
		}

		return responseEntity;
	}

	
	
}
