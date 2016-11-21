package com.test.entity;

public class Conditions {
	private String name;		//搜索字段
	private Object value;		//搜索条件
	private int type;		//搜索类型,1.equal、2.like、3.between,

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}