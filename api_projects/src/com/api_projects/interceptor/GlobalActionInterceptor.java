package com.api_projects.interceptor;

import java.util.Map;
import java.util.Set;

import com.api_projects.common.StaticObject;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class GlobalActionInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		Controller controller = inv.getController();
		Set<Map.Entry<String, String>> entryseSet = StaticObject.sessionMap.entrySet();
		for (Map.Entry<String, String> entry : entryseSet) {
			controller.setSessionAttr(entry.getKey(), entry.getValue());
		}
		inv.invoke();
	}
}