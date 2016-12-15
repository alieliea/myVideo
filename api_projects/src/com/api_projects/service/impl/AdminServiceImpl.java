package com.api_projects.service.impl;

import com.api_projects.model.Admin;
import com.api_projects.service.AdminService;
import com.api_projects.util.MD5Util;
import com.jfinal.plugin.activerecord.Page;

public class AdminServiceImpl implements AdminService {
	
	public Admin dao = Admin.dao;

	@Override
	public Admin checkLogin(String name, String pass) {
		return dao.findFirst("select * from api_admin where username=? and userpass=?", name, MD5Util.GetMD5Code(pass));
	}

	@Override
	public Page<Admin> searchAdmins(Page<Admin> page) {
		return dao.paginate(page.getPageNumber(), page.getPageSize(), "select * ", " from api_admin");
	}

	@Override
	public Admin checkName(String name) {
		return dao.findFirst("select * from api_admin where username=?", name);
	}

	@Override
	public boolean initAdmin(Integer id) {
		Admin admin = dao.findById(id);
		admin.setUserpass(MD5Util.GetMD5Code("111111"));
		return admin.update();
	}

	@Override
	public Admin dao() {
		return dao;
	}
}