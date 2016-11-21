package com.api_projects.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;

public class StaticObject {
	public static final String LOGINID = "loginId";
	public static final String CAPTCHA = "captcha";
	public static final String LOGINUSER = "loginuser";
	public static final String USERNAME = "rememberName";
	public static Map<String, String> sessionMap = new HashMap<String, String>();
	public static HashMap<String, String> extMap = new HashMap<String, String>();
	public static String savePath;
	public static String saveUrl;
	public static long maxSize;

	public static String[] ALLOBJECT;

	public static void init() {
		ALLOBJECT = new String[3];
		ALLOBJECT[0] = LOGINID;
		ALLOBJECT[1] = CAPTCHA;
		ALLOBJECT[2] = LOGINUSER;
		
		Prop prop = PropKit.use("config.txt");
		savePath = prop.get("file_path");
		saveUrl = prop.get("file_url");
		maxSize = Long.valueOf(prop.get("maxSize"));
		
		Set<Entry<Object, Object>> proSet = prop.getProperties().entrySet();
		for (Map.Entry<Object, Object> entry : proSet) {
			String key = entry.getKey().toString();
			if (key.indexOf("_url") != -1) {
				sessionMap.put(key, entry.getValue().toString());
			}
			if(key.indexOf("up_") != -1){
				extMap.put(key.replace("up_", ""), entry.getValue().toString());
			}
		}
	}
}