package com.api_projects.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseAdmin<M extends BaseAdmin<M>> extends Model<M> implements IBean {

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

	public void setProjects(java.lang.String projects) {
		set("projects", projects);
	}

	public java.lang.String getProjects() {
		return get("projects");
	}

	public void setStatus(java.lang.Integer status) {
		set("status", status);
	}

	public java.lang.Integer getStatus() {
		return get("status");
	}

	public void setRank(java.lang.Integer rank) {
		set("rank", rank);
	}

	public java.lang.Integer getRank() {
		return get("rank");
	}

}