package com.api_projects.config;

import java.util.Timer;

import com.api_projects.action.AdminAction;
import com.api_projects.action.ApiAction;
import com.api_projects.action.ProjectsAction;
import com.api_projects.common.StaticObject;
import com.api_projects.common.Task;
import com.api_projects.interceptor.GlobalActionInterceptor;
import com.api_projects.model._MappingKit;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;

public class BaseConfig extends JFinalConfig {
	private Timer timer = new Timer();
	
	public void configConstant(Constants me) {
		PropKit.use("config.txt");
		me.setDevMode(true);
		me.setViewType(ViewType.JSP);
		me.setBaseViewPath("/WEB-INF/jsp");
	}

	public void configRoute(Routes me) {
		me.add("/admin", AdminAction.class,"/");
		me.add("/api", ApiAction.class,"/");
		me.add("/projects", ProjectsAction.class,"/projects");
	}
	public static C3p0Plugin createC3p0Plugin() {
		return new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
	}
	
	public void configPlugin(Plugins me) {
		C3p0Plugin c3p0Plugin = createC3p0Plugin();
		me.add(c3p0Plugin);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		arp.setShowSql(PropKit.getBoolean("showSql"));
		me.add(arp);
		_MappingKit.mapping(arp);
		StaticObject.init();//初始化
	}

	public void configInterceptor(Interceptors me) {
		me.addGlobalActionInterceptor(new GlobalActionInterceptor());
	}

	public void configHandler(Handlers me) {
		
	}

	public static void main(String[] args) {
		JFinal.start("WebRoot", 80, "/", 5);
	}
	
	@Override
    public void afterJFinalStart() {
        super.afterJFinalStart();
        timer.schedule(new Task(), 0, 3600000);
    }
	
	@Override
    public void beforeJFinalStop() {
        super.beforeJFinalStop();
        timer.cancel();
    }
}