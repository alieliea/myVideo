package com.test.db;

import java.io.File;
import java.io.IOException;

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
}