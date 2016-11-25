package com.api_projects.model.base;

import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseApiInfo<M extends BaseApiInfo<M>> extends BaseModel<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setProjectId(java.lang.Integer projectId) {
		set("project_id", projectId);
	}

	public java.lang.Integer getProjectId() {
		return get("project_id");
	}

	public void setUserId(java.lang.Integer userId) {
		set("user_id", userId);
	}

	public java.lang.Integer getLastEdit() {
		return get("lastEdit");
	}

	public void setLastEdit(java.lang.Integer lastEdit) {
		set("lastEdit", lastEdit);
	}

	public java.lang.Integer getUserId() {
		return get("user_id");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}

	public java.lang.String getName() {
		return get("name");
	}

	public void setDetail(java.lang.String detail) {
		set("detail", detail);
	}

	public java.lang.String getDetail() {
		return get("detail");
	}

	public void setStatus(java.lang.Integer status) {
		set("status", status);
	}

	public java.lang.Integer getStatus() {
		return get("status");
	}

	public void setDoUrl(java.lang.String doUrl) {
		set("doUrl", doUrl);
	}

	public java.lang.String getDoUrl() {
		return get("doUrl");
	}
	
	public void setMaxpage(java.lang.Integer maxpage) {
		set("maxpage", maxpage);
	}

	public java.lang.Integer getMaxpage() {
		return get("maxpage");
	}
	
	public void setSubject(java.lang.String subject) {
		set("subject", subject);
	}

	public java.lang.String getSubject() {
		return get("subject");
	}

}
