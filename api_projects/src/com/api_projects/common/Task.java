package com.api_projects.common;

import java.util.TimerTask;

import com.api_projects.model.Admin;

public class Task extends TimerTask {

	public void run() {
		System.out.println("定时任务执行");
        StaticObject.userList = Admin.dao.find("select * from api_admin where rank=1 and status=1");
	}
}