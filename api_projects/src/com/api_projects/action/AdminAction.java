package com.api_projects.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.api_projects.common.StaticObject;
import com.api_projects.interceptor.LoginInterceptor;
import com.api_projects.model.Admin;
import com.api_projects.model.Projects;
import com.api_projects.service.AdminService;
import com.api_projects.service.ProjectService;
import com.api_projects.service.impl.AdminServiceImpl;
import com.api_projects.service.impl.ProjectServiceImpl;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

public class AdminAction extends Controller {

	private AdminService adminService = new AdminServiceImpl();
	private ProjectService projectService = new ProjectServiceImpl();
	private Map<String, Object> result = new HashMap<String, Object>();

	public void toLogin() {
		this.clearSessions();
		render("login.jsp");
	}

	public void login() {
		Admin admin = adminService.checkLogin(getPara("username"), getPara("userpass"));
		if (admin != null) {
			result.put("success", true);
			result.put("msg", "登陆成功");
			setSessionAttr(StaticObject.LOGINUSER, admin);
			setSessionAttr(StaticObject.LOGINID, admin.getId());
			setSessionAttr(StaticObject.USERNAME, admin.getUsername());
		} else {
			result.put("success", false);
			result.put("msg", "用户名密码错误!!");
		}
		renderJson(result);
	}

	@Before(LoginInterceptor.class)
	public void main() {
		List<Projects> list = projectService.getAllList();
		setAttr("projects", list);
		render("main.jsp");
	}
	
	private void clearSessions() {
		for (String object : StaticObject.ALLOBJECT) {
			removeSessionAttr(object);
		}
	}
}