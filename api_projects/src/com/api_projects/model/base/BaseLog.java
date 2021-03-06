package com.api_projects.model.base;

import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseLog<M extends BaseLog<M>> extends BaseModel<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setUserid(java.lang.Integer userid) {
		set("userid", userid);
	}

	public java.lang.Integer getUserid() {
		return get("userid");
	}

	public void setUsername(java.lang.String username) {
		set("username", username);
	}

	public java.lang.String getUsername() {
		return get("username");
	}

	public void setInfo(java.lang.String info) {
		set("info", info);
	}

	public java.lang.String getInfo() {
		return get("info");
	}

	public void setTime(java.util.Date time) {
		set("time", time);
	}

	public java.util.Date getTime() {
		return get("time");
	}

	public void setProjectsId(java.lang.Integer projectsId) {
		set("projects_id", projectsId);
	}

	public java.lang.Integer getProjectsId() {
		return get("projects_id");
	}


	public void setApiId(java.lang.Integer apiId) {
		set("api_id", apiId);
	}

	public java.lang.Integer getApiId() {
		return get("api_id");
	}

}
