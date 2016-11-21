package com.mytest.action.system.login;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import com.jfinal.core.Controller;
import com.mytest.common.StaticObject;
import com.mytest.model.system.Log;
import com.mytest.model.system.Menu;
import com.mytest.model.system.User;
import com.mytest.util.CMDUtil;
import com.mytest.util.MD5Util;
import com.mytest.util.MyCaptchaRender;

public class LoginAction extends Controller {

	public void index() {
		render("login.jsp");
	}

	public void login() {
		String inputRandomCode = getPara("captcha");
		boolean validate = MyCaptchaRender.validate(this, inputRandomCode);
		String username = getPara("username");
		String userpass = getPara("userpass");
		Map<String, Object> result = new HashMap<String, Object>();
		String ip = getRequest().getRemoteAddr();
		String mac = "";
		String browser = getRequest().getHeader("user-agent");
		try {
			mac =  CMDUtil.getMacAddress(getRequest().getRemoteHost());
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean success = true;
		String msg = "";
		if (validate) {
			User user = User.dao.checkLogin(username,
					MD5Util.GetMD5Code(userpass));
			if (user == null) {
				success = false;
				msg = "用户名不存在！";
			} else {
				int loginTime = user.getLoginTime();
				int isFreezon = user.getIsFreezon();
				if (user.getVerdict() == 1) {
					Log.dao.saveLog(user.getId(), user.getUsername(), getRequest(), "输错密码");
					if (loginTime == 0 || isFreezon == 1) {
						if (isFreezon == 0) {
							user.setIsFreezon(1);
							user.update();
						}
						success = false;
						msg = "密码输错超过5次，账户已经冻结！";
					} else {
						user.setLoginTime(loginTime - 1);
						user.update();
						success = false;
						msg = "密码错误！还有" + (loginTime - 1) + "次机会！";
					}
				} else {
					if (loginTime != 5) {
						user.setLoginTime(5);
					}
					user.setLastLogin(new Date());
					user.setLastIp(ip);
					user.setMac(mac);
					user.setBrowser(browser);
					user.update();
					Log.dao.saveLog(user.getId(), user.getUsername(), getRequest(), "登录系统");
					setSessionAttr(StaticObject.LOGINID, user.getId());
					setSessionAttr(StaticObject.LOGINUSER, user);
					List<Menu> list = Menu.dao.getAllMenuList(user.getId());
					setSessionAttr("leftMenuList", list);
					msg = "登录成功！";
				}
			}
		} else {
			success = false;
			msg = "验证码错误！";
		}
		result.put("success", success);
		result.put("msg", msg);
		renderJson(result);
	}

	/**
	 * 登录设置验证码cookie失效
	 */
	public void main() {
		Cookie cookie = new Cookie(StaticObject.CAPTCHA, "");
		cookie.setPath("/");
		cookie.setMaxAge(0);
		getResponse().addCookie(cookie);
		redirect("/hello");
	}

	public void logOut() {
		this.clearSessions();
		redirect("/system/login");
	}
	
	public void captcha() {
		render(new MyCaptchaRender(60, 22, 4, true));
	}

	private void clearSessions() {
		for (String object : StaticObject.ALLOBJECT) {
			removeSessionAttr(object);
		}
	}
}