package com.api_projects.action;

import java.util.HashMap;
import java.util.Map;

import com.api_projects.model.Admin;
import com.api_projects.service.AdminService;
import com.api_projects.service.impl.AdminServiceImpl;
import com.jfinal.core.Controller;

public class AdminAction extends Controller{
	
	private AdminService adminService = new AdminServiceImpl();
	private Map<String, Object> result = new HashMap<String, Object>();
	
	public void toLogin(){
		render("login.jsp");
	}
	
	public void login(){
		Admin admin = adminService.checkLogin(getPara("name"), getPara("pass"));
		if(admin != null){
			result.put("success", true);
		}else{
			result.put("success", false);
			result.put("msg", "用户名密码错误!!");
		}
		renderJson(result);
	}
}