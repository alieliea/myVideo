package com.api_projects.action;

import com.api_projects.service.AdminService;
import com.api_projects.service.impl.AdminServiceImpl;
import com.jfinal.core.Controller;

public class AdminAction extends Controller{
	
	private AdminService adminService = new AdminServiceImpl();
	
	public void aaa(){
		System.out.println(123);
		renderText(adminService.checkName("admin").getUsername() + "::::" + adminService.dao().findById(1).getUsername());
	}
}