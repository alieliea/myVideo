package com.mytest.action.system.main;

import com.jfinal.core.Controller;
import com.mytest.model.system.SystemIcon;

public class SystemIconAction extends Controller{
	
	public void index(){
		setAttr("icons", SystemIcon.dao.iconList(getParaToInt(0,1), getParaToInt(1,187)));
		render("icon.jsp");
	}
}