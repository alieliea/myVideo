package com.mytest.model.base.system;

import com.jfinal.plugin.activerecord.IBean;
import com.mytest.model.base.BaseModel;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseUser<M extends BaseUser<M>> extends BaseModel<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setUsername(java.lang.String username) {
		set("username", username);
	}

	public java.lang.String getUsername() {
		return get("username");
	}

	public void setUserpass(java.lang.String userpass) {
		set("userpass", userpass);
	}

	public java.lang.String getUserpass() {
		return get("userpass");
	}

	public void setStatus(java.lang.Integer status) {
		set("status", status);
	}

	public java.lang.Integer getStatus() {
		return get("status");
	}

	public void setRealname(java.lang.String realname) {
		set("realname", realname);
	}

	public java.lang.String getRealname() {
		return get("realname");
	}

	public void setAdmin(java.lang.Integer admin) {
		set("admin", admin);
	}

	public java.lang.Integer getAdmin() {
		return get("admin");
	}

	public void setLastLogin(java.util.Date lastLogin) {
		set("lastLogin", lastLogin);
	}

	public java.util.Date getLastLogin() {
		return get("lastLogin");
	}

	public void setLastIp(java.lang.String lastIp) {
		set("lastIp", lastIp);
	}

	public java.lang.String getLastIp() {
		return get("lastIp");
	}

	public void setMac(java.lang.String mac) {
		set("mac", mac);
	}

	public java.lang.String getMac() {
		return get("mac");
	}

	public void setBrowser(java.lang.String browser) {
		set("browser", browser);
	}

	public java.lang.String getBrowser() {
		return get("browser");
	}

	public void setLoginTime(java.lang.Integer loginTime) {
		set("loginTime", loginTime);
	}

	public java.lang.Integer getLoginTime() {
		return get("loginTime");
	}

	public void setFirstLogin(java.lang.Integer firstLogin) {
		set("firstLogin", firstLogin);
	}

	public java.lang.Integer getFirstLogin() {
		return get("firstLogin");
	}

	public void setIsFreezon(java.lang.Integer isFreezon) {
		set("isFreezon", isFreezon);
	}

	public java.lang.Integer getIsFreezon() {
		return get("isFreezon");
	}

	public void setFatherId(java.lang.Integer fatherId) {
		set("father_id", fatherId);
	}

	public java.lang.Integer getFatherId() {
		return get("father_id");
	}
	
	public void setUserhead(String userhead){
		set("userhead",userhead);
	}
	
	public String getUserhead(){
		return get("userhead");
	}

}
