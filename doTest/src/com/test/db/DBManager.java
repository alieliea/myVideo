package com.test.db;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.test.db.impl.TestDaoImpl;
import com.test.entity.Test;
import com.test.util.Pages;

public class DBManager {

	public static File DB;
	public static String DB_Name = "DB";

	/**
	 * 初始化数据库
	 */
	public static void init() {
		DB = new File(DB_Name);
		if (!DB.exists()) {
			DB.mkdir();
		}
	}

	/**
	 * 清空数据库
	 */
	public static void cleanDB() {
		DB.delete();
	}

	/**
	 * 清空表
	 * 
	 * @param tableName
	 */
	public static void cleanTable(String tableName) {
		File table = new File(DB_Name + "/" + tableName);
		table.delete();
	}

	/**
	 * 新建表
	 * 
	 * @param tableName
	 */
	public static void createTable(String tableName) {
		File table = new File(DB_Name + "/" + tableName);
		if (!table.exists()) {
			try {
				table.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static File getTable(String tableName){
		File table = new File(DB_Name + "/" + tableName);
		return table;
	}

	public static void main(String[] args) {
		TestDao dao = new TestDaoImpl(Test.class);
//		Test test = dao.findById(4);
//		System.out.println(test == null? "" : test.getA());
////		test = new Test();
////		dao.insert(test);
//		List<Test> list = dao.getALL();
//		if(list != null && list.size() > 0){
//			for(Test t : list){
//				System.out.println(t.getA());
//			}
//		}
//		test.setA(15);
//		dao.update(test);
//		dao.delete(5);
		Pages<Test> page = new Pages<>();
		page.setPage(3);
		page.setRows(2);
		List<Test> list = dao.getALLByPage(page).getList();
		if(list != null && list.size() > 0){
			for(Test t : list){
				System.out.println(t.getA());
			}
		}
	}
}