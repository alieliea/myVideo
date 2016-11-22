package com.api_projects.service;

import java.util.List;

import com.api_projects.model.ApiInOut;
import com.api_projects.model.ApiInfo;
import com.api_projects.model.Projects;
import com.jfinal.plugin.activerecord.Page;

public interface ProjectService {
	
	public Projects dao();
	
	public List<Projects> getList(String ids);
	
	public List<Projects> getAllList();
	
	public ApiInfo apiDao();
	
	public ApiInOut apiInOutDao();
	
	public Page<ApiInfo> apiInfoPages(int pageNumber,int pageSize,int projectsId,String name,Integer status,String url,Integer userid);
	
	/**
	 * 获取api参数详情
	 * @param apiId
	 * @param inOut 0传入参数，1传出参数
	 * @return
	 */
	public List<ApiInOut> apiInOutList(int apiId, int inOut);
	
	public int deleteApiInOutByApiId(int apiId);
	
}