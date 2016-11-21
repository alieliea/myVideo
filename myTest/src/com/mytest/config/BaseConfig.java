package com.mytest.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.mytest.action.Hello;
import com.mytest.action.system.UploadFiles;
import com.mytest.action.system.login.LoginAction;
import com.mytest.action.system.login.RegisterAction;
import com.mytest.action.system.main.FilesAction;
import com.mytest.action.system.main.MenuAction;
import com.mytest.action.system.main.SystemIconAction;
import com.mytest.common.StaticObject;
import com.mytest.interceptor.GlobalActionInterceptor;
import com.mytest.model._MappingKit;

public class BaseConfig extends JFinalConfig {
	
	public void configConstant(Constants me) {
		PropKit.use("config.txt");
		me.setDevMode(true);
		me.setViewType(ViewType.JSP);
		me.setBaseViewPath("/WEB-INF/jsp");
	}

	public void configRoute(Routes me) {
		 me.add("/hello", Hello.class,"/admin/system/");
		 me.add("/system/login", LoginAction.class,"/");
		 me.add("/system/register", RegisterAction.class,"/");
		 me.add("/system/menu", MenuAction.class,"/admin/menu/");
		 me.add("/upfiles", UploadFiles.class);
		 me.add("/files", FilesAction.class,"/admin/up/");
		 me.add("/system/icon", SystemIconAction.class,"/admin/menu/");
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
}