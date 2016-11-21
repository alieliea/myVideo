package com.mytest.action.system.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.mytest.common.StaticObject;
import com.mytest.interceptor.LoginInterceptor;
import com.mytest.model.system.Log;
import com.mytest.model.system.User;
import com.mytest.model.web.Files;

@Before(LoginInterceptor.class)
public class FilesAction extends Controller{
	
	public void index(){
		String uuid = getPara("uuid","");
		List<Files> list = Files.dao.getFiles(uuid);
		setAttr("list", list);
		setAttr("uuid", uuid);
		render("files.jsp");
	}
	
	public void saveFiles(){
		Files files = new Files();
		files.setName(getPara("name"));
		files.setRealname(getPara("realname"));
		files.setPath(getPara("path"));
		files.setUuid(getPara("uuid"));
		files.save();
		User user = User.dao.findById(getSessionAttr(StaticObject.LOGINID));
		Log.dao.saveLog(user.getId(), user.getUsername(), getRequest(), "上传文件--" + getPara("name"));
		renderJson();
	}
	
	public void delFiles(){
		int id = getParaToInt("id",0);
		boolean success = true;
		String msg = "";
		if(id == 0){
			success = false;
			msg = "id为空";
		}else{
			success = Files.dao.deleteById(id);
			if(success){
				msg = "删除成功";
				User user = User.dao.findById(getSessionAttr(StaticObject.LOGINID));
				Log.dao.saveLog(user.getId(), user.getUsername(), getRequest(), "删除文件--" + Files.dao.findById(id).getName());
			}else{
				msg = "删除失败";
			}
		}
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("success", success);
		obj.put("msg", msg);
		renderJson(obj);
	}
}