package com.mytest.model.base.web;

import com.jfinal.plugin.activerecord.IBean;
import com.mytest.model.base.BaseModel;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseCategories<M extends BaseCategories<M>> extends BaseModel<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}

	public java.lang.String getName() {
		return get("name");
	}

	public void setShowOrder(java.lang.Integer showOrder) {
		set("show_order", showOrder);
	}

	public java.lang.Integer getShowOrder() {
		return get("show_order");
	}

	public void setEname(java.lang.String ename) {
		set("ename", ename);
	}

	public java.lang.String getEname() {
		return get("ename");
	}

	public void setStatus(java.lang.Integer status) {
		set("status", status);
	}

	public java.lang.Integer getStatus() {
		return get("status");
	}

}