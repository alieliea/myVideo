package com.api_projects.service.impl;

import java.util.List;

import com.api_projects.model.ApiInOut;
import com.api_projects.model.ApiInfo;
import com.api_projects.model.Projects;
import com.api_projects.service.ProjectService;
import com.api_projects.util.StringUtil;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

public class ProjectServiceImpl implements ProjectService {

	private Projects dao = Projects.dao;
	private ApiInfo apiDao = ApiInfo.dao;
	private ApiInOut apiInOutDao = ApiInOut.dao;

	@Override
	public Projects dao() {
		return dao;
	}

	@Override
	public ApiInfo apiDao() {
		return apiDao;
	}

	@Override
	public ApiInOut apiInOutDao() {
		return apiInOutDao;
	}

	@Override
	public List<Projects> getList(String ids) {
		return dao.find("select * from api_projects where id in (" + ids + ")");
	}

	@Override
	public List<Projects> getAllList() {
		return dao.find("select * from api_projects where show=0");
	}

	@Override
	public Page<ApiInfo> apiInfoPages(int pageNumbser, int pageSize, int projectsId, String name, Integer status,
			String url, Integer userid) {
		String from = " from api_api_info as obj ";
		String condition = " where obj.project_id=? ";
		if (!StringUtil.isEmptyStr(name)) {
			condition += " and (obj.name like '%" + name + "%' or obj.detail like '%" + name + "%')";
		}
		if (status != null) {
			condition += " and obj.status=" + status;
		}
		if (!StringUtil.isEmptyStr(url)) {
			condition += " and obj.doUrl like '%" + url + "%'";
		}
		return apiDao.paginate(pageNumbser, pageSize, "select obj.* ", from + condition, projectsId);
	}

	@Override
	public List<ApiInOut> apiInOutList(int apiId, int inOut) {
		return apiInOutDao.find("select * from api_api_in_out where nature=? and api_id=?", inOut, apiId);
	}

	@Override
	public int deleteApiInOutByApiId(int apiId) {
		return Db.update("delete from api_api_in_out where api_id=?", apiId);
	}

}