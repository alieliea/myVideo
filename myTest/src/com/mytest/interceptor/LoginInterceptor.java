package com.mytest.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.mytest.common.StaticObject;

public class LoginInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		Controller controller = inv.getController();
		Integer sessionId = controller.getSessionAttr(StaticObject.LOGINID);
		if(sessionId != null){
			inv.invoke();
		}else{
			controller.redirect("/system/login");
		}
	}
}