package com.api_projects.service.impl;

import javax.servlet.http.HttpSession;

import com.api_projects.common.StaticObject;
import com.api_projects.model.Admin;
import com.api_projects.model.Log;
import com.api_projects.service.LogService;
import com.api_projects.util.SystemDateFormatUtil;

public class LogServiceImpl implements LogService {

	private Log dao = Log.dao;

	@Override
	public Log dao() {
		return dao;
	}
	
	@Override
	public void saveLog(HttpSession session, String info, Integer projectsId, Integer apiId) {
		Admin admin = (Admin) session.getAttribute(StaticObject.LOGINUSER);
		Log log = new Log();
		log.setUserid(admin.getId());
		log.setUsername(admin.getRealname());
		log.setTime(SystemDateFormatUtil.getDefTimestamp());
		log.setInfo(info);
		log.setProjectsId(projectsId);
		log.setApiId(apiId);
		log.save();
	}
}