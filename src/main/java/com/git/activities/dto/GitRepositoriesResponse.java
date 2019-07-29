package com.git.activities.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class GitRepositoriesResponse {

	
    private Long id;
    private String name;
    private Date created_at;
    private Date updated_at;
    List<CommitsResponse> gitdailycommits = new ArrayList<>();
    
	
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public List<CommitsResponse> getGitdailycommits() {
		return gitdailycommits;
	}
	public void setGitdailycommits(List<CommitsResponse> gitdailycommits) {
		this.gitdailycommits = gitdailycommits;
	}
	
}

    