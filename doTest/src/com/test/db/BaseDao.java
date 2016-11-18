package com.test.db;

import java.util.List;

public interface BaseDao <T>{


	/**
	 * 插入数据
	 * @param T
	 */
	public abstract boolean insert(T t);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public abstract T findById(Object id);
	
	/**
	 * 获取全部
	 * @return
	 */
	public abstract List<T> getALL();
	
	/**
	 * 更新数据
	 * @param t
	 * @return
	 */
	public abstract boolean update(T t);
	
	/**
	 * 删除数据
	 * @param id
	 * @return
	 */
	public abstract boolean delete(int id);
}