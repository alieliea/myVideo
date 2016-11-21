package com.api_projects.service;

import com.api_projects.model.Admin;
import com.jfinal.plugin.activerecord.Page;

public interface AdminService{
	public Admin checkLogin(String name,String pass);

	public Page<Admin> searchAdmins(Page<Admin> page);

	public Admin checkName(String name);
	
	public boolean initAdmin(Integer id);
	
	public Admin dao();
}