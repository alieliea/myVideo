package com.tinyDb.util;

import java.util.List;

public class Pages<T> {
	private int page = 1;
	private int rows = 10;
	private int count = 0;
	private List<T> list;
	private List<Conditions> conditions;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getFirstResult() {
		return (getPage() - 1) * getRows();
	}

	public int getPageCount() {
		int pageCount = getCount() / getRows() + (getCount() % getRows() == 0 ? 0 : 1);
		return pageCount;
	}

	public int getMaxResult() {
		int max = (getPage() - 1) * getRows() + getRows();
		max = max > count ? count : max;
		return max;
	}

	public List<Conditions> getConditions() {
		return conditions;
	}

	public void setConditions(List<Conditions> conditions) {
		this.conditions = conditions;
	}
}