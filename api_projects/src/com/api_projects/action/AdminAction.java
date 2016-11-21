package com.api_projects.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.api_projects.common.StaticObject;
import com.api_projects.model.Admin;
import com.api_projects.service.AdminService;
import com.api_projects.service.impl.AdminServiceImpl;
import com.api_projects.util.AESOperator;
import com.api_projects.util.TrustSSL;
import com.jfinal.core.Controller;

import net.sf.json.JSONObject;

public class AdminAction extends Controller {

	private AdminService adminService = new AdminServiceImpl();
	private Map<String, Object> result = new HashMap<String, Object>();

	public void toLogin() {
		render("login.jsp");
	}

	public void login() {
		Admin admin = adminService.checkLogin(getPara("username"), getPara("userpass"));
		if (admin != null) {
			result.put("success", true);
			result.put("msg", "登陆成功");
			setSessionAttr(StaticObject.LOGINUSER, admin);
			setSessionAttr(StaticObject.LOGINID, admin.getId());
			setSessionAttr(StaticObject.USERNAME, admin.getUsername());
		} else {
			result.put("success", false);
			result.put("msg", "用户名密码错误!!");
		}
		renderJson(result);
	}

	public void main() {
		String url = getPara("api_url");
		String doUrl = getPara("api_doUrl");
		String strURL = url + doUrl;
		Map<String, String[]> param = getParaMap();
		JSONObject obj = new JSONObject();
		Iterator<String> it = param.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			if(!key.equals("api_url") && !key.equals("api_doUrl")){
				obj.put(key, param.get(key)[0]);
			}
		}
		System.out.println(obj.toString());
		String enString;
		String results = "";
		try {
			enString = AESOperator.getInstance().encrypt(obj.toString());
			results = TrustSSL.httpPost(strURL, enString);
		} catch (Exception e) {
			e.printStackTrace();
		} // 加密
		result.put("param", obj.toString());
		result.put("result", results);
		renderJson(result);
	}
	
	public void logOut() {
		this.clearSessions();
		redirect("/admin/toLogin");
	}
	
	private void clearSessions() {
		for (String object : StaticObject.ALLOBJECT) {
			removeSessionAttr(object);
		}
	}
}