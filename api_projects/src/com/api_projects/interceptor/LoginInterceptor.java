package com.api_projects.interceptor;

import com.api_projects.common.StaticObject;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class LoginInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		Controller controller = inv.getController();
		Integer sessionId = controller.getSessionAttr(StaticObject.LOGINID);
		if(sessionId != null){
			inv.invoke();
		}else{
			controller.redirect("/admin/toLogin");
		}
	}
}