package com.api_projects.service;

import java.util.List;

import com.api_projects.model.Projects;

public interface ProjectService {
	
	public Projects dao();
	
	public List<Projects> getList(String ids);
}