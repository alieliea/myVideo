package com.mytest.action.system.login;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.core.Controller;
import com.mytest.model.system.User;
import com.mytest.model.system.UserMenu;
import com.mytest.util.MD5Util;

public class RegisterAction extends Controller {
	
	public void index() {
		render("register.jsp");
	}
	
	public void register(){
		String username = getPara("username");
		String userpass = getPara("userpass");
		String msg = "";
		Map<String, Object> result = new HashMap<String, Object>();
		User user = new User().set("username", username).set("userpass", MD5Util.GetMD5Code(userpass));
		boolean success = user.save();
		if(success){
			UserMenu.dao.doUserMenuByUserId(user.getId());
			msg = "注册成功！跳转登录";
		}else{
			msg = "注册失败！";
		}
		result.put("success", success);
		result.put("msg", msg);
		renderJson(result);
	}
	
	/**
	 * 查询用户名是否已被注册
	 */
	public void checkUserName(){
		String username = getPara("username");
		boolean success = User.dao.checkJuna(username);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", success);
		renderJson(result);
	}
}