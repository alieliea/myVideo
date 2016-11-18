package com.test.db.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.test.db.BaseDao;
import com.test.db.DBManager;
import com.test.util.Pages;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	private static String tableName;
	private static String tables;
	private Class<T> t_class;

	@Override
	public boolean insert(T t) {
		FileWriter writer = null;
		try {
			JSONObject object = objectToJson(t);
			int id = this.serialNum() + 1;
			JSONObject entity = object.getJSONObject(tableName);
			if (entity.get("id") != null && !entity.get("id").equals("") && findById(entity.get("id")) != null) {
				System.err.println("违反唯一主键规则");
				return false;
			} else {
				entity.put("id", id);
				writer = new FileWriter(tables, true);
				writer.write("\r\n" + entity.toString());
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public boolean delete(int id) {
		FileWriter writer = null;
		try {
			if (findById(id) == null) {
				System.err.println("主键错误");
				return false;
			} else {
				String[] values = getALLStr().split("\r\n");
				String newValues = "";
				for (String value : values) {
					JSONObject v;
					try {
						v = StringToJson(value);
						if (v.getInt("id") != id) {
							newValues += v + "\r\n";
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				writer = new FileWriter(tables, false);
				writer.write(newValues);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean update(T t) {
		JSONObject object;
		FileWriter writer = null;
		try {
			object = objectToJson(t);
			JSONObject entity = object.getJSONObject(tableName);
			if (entity.get("id") == null || entity.get("id").equals("") || findById(entity.get("id")) == null) {
				System.err.println("主键错误");
				return false;
			} else {
				String[] values = getALLStr().split("\r\n");
				String newValues = "";
				for (String value : values) {
					JSONObject v;
					try {
						v = StringToJson(value);
						if (v.getInt("id") == entity.getInt("id")) {
							newValues += entity.toString() + "\r\n";
						} else {
							newValues += v + "\r\n";
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				writer = new FileWriter(tables, false);
				writer.write(newValues);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public List<T> getALL() {
		String[] value = getALLStr().split("\r\n");
		List<T> list = new ArrayList<T>();
		for (String v : value) {
			list.add((T) JSONToObj(v, t_class));
		}
		return list;
	}

	@Override
	public Pages<T> getALLByPage(Pages<T> pages) {
		String[] value = getALLStr().split("\r\n");
		pages.setCount(value.length);
		List<T> list = new ArrayList<>();
		for (int i = pages.getFirstResult(); i < pages.getMaxResult(); i++) {
			list.add((T) JSONToObj(value[i], t_class));
		}
		pages.setList(list);
		return pages;
	}
	
	@Override
	public Pages<T> searchByPage_conditions(Pages<T> pages,Map<String, Object> conditions) {
		String[] value = getALLStr().split("\r\n");
		pages.setCount(value.length);
		List<T> list = new ArrayList<>();
		for (int i = pages.getFirstResult(); i < pages.getMaxResult(); i++) {
			list.add((T) JSONToObj(value[i], t_class));
		}
		pages.setList(list);
		return pages;
	}

	@SuppressWarnings({ "resource" })
	@Override
	public T findById(Object id) {
		File file = DBManager.getTable(tableName);
		if (file.exists()) {
			BufferedReader br;
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				for (String line = br.readLine(); line != null; line = br.readLine()) {
					if (line.indexOf("{\"id\":" + id + ",") == 0) {
						return (T) JSONToObj(line, t_class);
					}
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			System.out.println("数据库不存在");
		}
		return null;
	}

	public static String getALLStr() {
		File file = DBManager.getTable(tableName);
		String str = "";
		if (file.exists()) {
			BufferedReader br;
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				for (String line = br.readLine(); line != null; line = br.readLine()) {
					str += line + "\r\n";
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			System.out.println("数据库不存在");
		}
		return str;
	}
	
	public static String getStrByCondition(Map<String, Object> conditions) {
		File file = DBManager.getTable(tableName);
		String str = "";
		if (file.exists()) {
			BufferedReader br;
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				for (String line = br.readLine(); line != null; line = br.readLine()) {
					str += line + "\r\n";
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			System.out.println("数据库不存在");
		}
		return str;
	}

	public int serialNum() {
		int num = 0;
		File file = DBManager.getTable(tableName);
		if (file.exists()) {
			BufferedReader br;
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				for (String line = br.readLine(); line != null; line = br.readLine()) {
					JSONObject object = StringToJson(line);
					if (object.getInt("id") > num) {
						num = object.getInt("id");
					}
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("数据库不存在");
		}
		return num;
	}

	public BaseDaoImpl(Class<T> t) {
		tableName = getTableName(t);
		tables = DBManager.DB_Name + "/" + tableName;
		this.t_class = t;
	}

	public String getTableName(Class<T> t) {
		String name = t.getName();
		name = name.substring(name.lastIndexOf(".") + 1);
		return name;
	}

	public static JSONObject StringToJson(String line) {
		JSONObject object = new JSONObject();
		object.put(tableName, line);
		return object.getJSONObject(tableName);
	}

	/**
	 * 将json转化为实体POJO
	 * 
	 * @param jsonStr
	 * @param obj
	 * @return
	 */
	public static <T> Object JSONToObj(String jsonStr, Class<T> obj) {
		T t = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			t = objectMapper.readValue(jsonStr, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 将实体POJO转化为JSON
	 * 
	 * @param obj
	 * @return
	 * @throws JSONException
	 * @throws IOException
	 */
	public static <T> JSONObject objectToJson(T obj) throws JSONException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = "";
		try {
			jsonStr = mapper.writeValueAsString(obj);
		} catch (IOException e) {
			throw e;
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate(tableName, jsonStr);
		return jsonObject;
	}
}