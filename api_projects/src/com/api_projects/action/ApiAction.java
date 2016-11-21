package com.api_projects.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.api_projects.util.AESOperator;
import com.api_projects.util.TrustSSL;
import com.jfinal.core.Controller;

import net.sf.json.JSONObject;

public class ApiAction extends Controller{

	private Map<String, Object> result = new HashMap<String, Object>();
	
	public void doTestApi(){
		String url = getPara("api_url");
		String doUrl = getPara("api_doUrl");
		String strURL = url + doUrl;
		Map<String, String[]> param = getParaMap();
		JSONObject obj = new JSONObject();
		Iterator<String> it = param.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			if(!key.equals("api_url") && !key.equals("api_doUrl")){
				obj.put(key, param.get(key)[0]);
			}
		}
		System.out.println(obj.toString());
		String enString;
		String results = "";
		try {
			enString = AESOperator.getInstance().encrypt(obj.toString());
			results = TrustSSL.httpPost(strURL, enString);
		} catch (Exception e) {
			e.printStackTrace();
		} // 加密
		result.put("param", obj.toString());
		result.put("result", results);
		renderJson(result);
	}
}