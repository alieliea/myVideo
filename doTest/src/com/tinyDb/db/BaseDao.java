package com.tinyDb.db;

import java.util.List;

import com.tinyDb.util.Conditions;
import com.tinyDb.util.Pages;

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
	
	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public abstract Pages<T> getALLByPage(Pages<T> pages);
	
	public abstract Pages<T> searchByPage_conditions(Pages<T> pages,List<Conditions> conditions);
}