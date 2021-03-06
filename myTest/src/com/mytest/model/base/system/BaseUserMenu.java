package com.mytest.model.base.system;

import com.jfinal.plugin.activerecord.IBean;
import com.mytest.model.base.BaseModel;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseUserMenu<M extends BaseUserMenu<M>> extends BaseModel<M> implements IBean {

	public void setUserId(java.lang.Integer userId) {
		set("user_id", userId);
	}

	public java.lang.Integer getUserId() {
		return get("user_id");
	}

	public void setMenuId(java.lang.Integer menuId) {
		set("menu_id", menuId);
	}

	public java.lang.Integer getMenuId() {
		return get("menu_id");
	}

	public void setAdd(java.lang.Integer add) {
		set("add", add);
	}

	public java.lang.Integer getAdd() {
		return get("add");
	}

	public void setEdit(java.lang.Integer edit) {
		set("edit", edit);
	}

	public java.lang.Integer getEdit() {
		return get("edit");
	}

	public void setDelete(java.lang.Integer delete) {
		set("delete", delete);
	}

	public java.lang.Integer getDelete() {
		return get("delete");
	}

	public void setStatus(java.lang.Integer status) {
		set("status", status);
	}

	public java.lang.Integer getStatus() {
		return get("status");
	}

}
