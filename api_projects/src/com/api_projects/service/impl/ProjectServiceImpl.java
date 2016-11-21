package com.api_projects.service.impl;

import java.util.List;

import com.api_projects.model.Projects;
import com.api_projects.service.ProjectService;

public class ProjectServiceImpl implements ProjectService {

	private Projects dao = Projects.dao;

	@Override
	public Projects dao() {
		return dao;
	}

	@Override
	public List<Projects> getList(String ids) {
		return dao.find("select * from api_projects where id in ("+ids+")");
	}
}