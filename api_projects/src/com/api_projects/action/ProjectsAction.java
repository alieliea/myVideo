package com.api_projects.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.api_projects.common.StaticObject;
import com.api_projects.interceptor.LoginInterceptor;
import com.api_projects.model.ApiInOut;
import com.api_projects.model.ApiInfo;
import com.api_projects.model.Projects;
import com.api_projects.service.LogService;
import com.api_projects.service.ProjectService;
import com.api_projects.service.impl.LogServiceImpl;
import com.api_projects.service.impl.ProjectServiceImpl;
import com.api_projects.util.StringUtil;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

@Before(LoginInterceptor.class)
public class ProjectsAction extends Controller {

	private ProjectService projectService = new ProjectServiceImpl();
	private Map<String, Object> result = new HashMap<String, Object>();
	private LogService logService = new LogServiceImpl();

	public void manage() {
		int projectsId = getParaToInt("projectsId");
		Projects projects = projectService.dao().findById(projectsId);
		int status = getParaToInt("status", -1);
		String url = getPara("url", "");
		String name = getPara("name", "");
		int userid = getParaToInt("userid", -1);
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 10);
		Page<ApiInfo> page = projectService.apiInfoPages(pageNumber, pageSize, projectsId, name, status, url, userid);
		setAttr("page", page);
		setAttr("projects", projects);
		setAttr("status", status);
		setAttr("url", url);
		setAttr("name", name);
		setAttr("userid", userid);
		setAttr("userlist", StaticObject.userList);
		render("manage.jsp");
	}

	public void addProjects() {
		int id = getParaToInt(0, 0);
		Projects projects = new Projects();
		if (id != 0) {
			projects = projectService.dao().findById(id);
		}
		setAttr("projects", projects);
		render("info.jsp");
	}

	public void saveProjects() {
		Projects projects = getModel(Projects.class);
		String info = "";
		if (projects.getId() == null || projects.getId() == 0) {
			info = "新增项目：" + projects.getName();
		} else {
			Projects old = projectService.dao().findById(projects.getId());
			info = "修改项目：" + old.getName() + "（测试地址：" + old.getTestUrl() + "||真实地址：" + old.getRealUrl() + "）--"
					+ projects.getName() + "（测试地址：" + projects.getTestUrl() + "||真实地址：" + projects.getRealUrl() + "）";
		}
		boolean success = projects.saveOrUpdate();
		logService.saveLog(getSession(), info, projects.getId(), null);
		result.put("success", success);
		renderJson(result);
	}

	public void addApiInfo() {
		int id = getParaToInt("apiId", 0);
		ApiInfo apiInfo = new ApiInfo();
		int userId = getSessionAttr(StaticObject.LOGINID);
		apiInfo.setUserId(userId);
		int isChange = 0;
		if (id != 0) {
			apiInfo = projectService.apiDao().findById(id);
			setAttr("inList", projectService.apiInOutList(id, 0));
			setAttr("outList", projectService.apiInOutList(id, 1));
			isChange = 1;
		}
		apiInfo.setLastEdit(userId);
		setAttr("apiInfo", apiInfo);
		setAttr("isChange", isChange);
		setAttr("projectsId", getParaToInt("projectsId"));
		render("apiInfo.jsp");
	}
	
	public void viewApiInfo(){
		int id = getParaToInt();
		ApiInfo apiInfo = projectService.apiDao().findById(id);
		setAttr("inList", projectService.apiInOutList(id, 0));
		setAttr("outList", projectService.apiInOutList(id, 1));
		setAttr("apiInfo", apiInfo);
		setAttr("projects", projectService.dao().findById(apiInfo.getProjectId()));
		render("view.jsp");
	}
	
	public void apiChange(){
		int id = getParaToInt("id");
		int status = getParaToInt("status");
		ApiInfo apiInfo = projectService.apiDao().findById(id);
		apiInfo.setStatus(status);
		apiInfo.setLastEdit(Integer.valueOf(getSessionAttr(StaticObject.LOGINID).toString()));
		result.put("success", apiInfo.saveOrUpdate());
		renderJson(result);
	}

	public void saveApiInfo() {
		ApiInfo apiInfo = getModel(ApiInfo.class);
		int isChange = getParaToInt("isChange");
		String[] api_request = new String[] { "非必要", "必要" };
		String[] api_nature = new String[] { "传入参数", "返回参数" };
		String info = "";
		if (apiInfo.getId() == null || apiInfo.getId().intValue() == 0) {
			info = "新增接口：" + apiInfo.getName();
		} else {
			ApiInfo old = projectService.apiDao().findById(apiInfo.getId());
			info = "修改接口：" + old.getName() + "（" + old.getDoUrl() + "||" + old.getDetail() + "）to " + apiInfo.getName()
					+ "（" + apiInfo.getDoUrl() + "||" + apiInfo.getDetail() + "）";
			if (isChange == 1) {
				List<ApiInOut> inlist = projectService.apiInOutList(apiInfo.getId(), 0);
				List<ApiInOut> outlist = projectService.apiInOutList(apiInfo.getId(), 1);
				String ins = "原传入参数：";
				String outs = "原返回参数：";
				if (inlist != null) {
					for (ApiInOut in : inlist) {
						ins += api_request[in.getRequest()] + " " + in.getGenre() + ":" + in.getName() + "--"
								+ in.getParticulars() + ",";
					}
				}
				if (outlist != null) {
					for (ApiInOut out : outlist) {
						ins += api_request[out.getRequest()] + " " + out.getGenre() + ":" + out.getName() + "--"
								+ out.getParticulars() + ",";
					}
				}
				info += ins + "||" + outs;
				projectService.deleteApiInOutByApiId(apiInfo.getId());
			}
		}
		boolean success = apiInfo.saveOrUpdate();
		if(!StringUtil.isEmptyStr(getPara("name"))){
			String[] names = getParaValues("name");
			String[] genres = getParaValues("genre");
			String[] particularss = getParaValues("particulars");
			String[] requests = getParaValues("request");
			String[] natures = getParaValues("nature");
			info += "...现参数：";
			for (int i = 0; i < names.length; i++) {
				ApiInOut apiInOut = new ApiInOut();
				apiInOut.setApiId(apiInfo.getId());
				apiInOut.setGenre(genres[i]);
				apiInOut.setName(names[i]);
				apiInOut.setParticulars(particularss[i]);
				apiInOut.setRequest(Integer.valueOf(requests[i]));
				apiInOut.setNature(Integer.valueOf(natures[i]));
				info += api_request[Integer.valueOf(requests[i])] + " " + api_nature[Integer.valueOf(natures[i])] + " "
						+ genres[i] + ":" + names[i] + "--" + particularss[i] + ",";
				success = apiInOut.save();
			}
		}
		logService.saveLog(getSession(), info, apiInfo.getProjectId(), apiInfo.getId());
		result.put("success", success);
		renderJson(result);
	}
}