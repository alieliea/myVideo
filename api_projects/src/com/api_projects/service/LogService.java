package com.api_projects.service;

import javax.servlet.http.HttpSession;

import com.api_projects.model.Log;

public interface LogService {

	public void saveLog(HttpSession session, String info, Integer projectsId, Integer apiId);

	public Log dao();
}