package com.mytest.action;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.mytest.interceptor.LoginInterceptor;

@Before(LoginInterceptor.class)
public class Hello extends Controller{
	
	public void index(){
		render("main.jsp");
	}
}