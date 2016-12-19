package com.api_projects.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.api_projects.util.StringUtil;
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
		String enString = "";
		while (it.hasNext()) {
			String key = it.next();
			if(!key.equals("api_url") && !key.equals("api_doUrl")){
				obj.put(key, param.get(key)[0]);
				enString += key + "=" + param.get(key)[0] + "&";
			}
		}
		System.out.println(strURL);
		System.out.println(obj.toString());
		System.out.println(enString);
		enString = StringUtil.isEmptyStr(enString) ? "" : enString.substring(0,enString.length() - 1);
		System.out.println(enString);
		//String enString;
		String results = "";
		try {
			//enString = obj.toString();//AESOperator.getInstance().encrypt(obj.toString());
			results = TrustSSL.httpRequest(strURL + "?" + enString);
		} catch (Exception e) {
			e.printStackTrace();
		} // 加密
		result.put("param", obj.toString());
		result.put("result", results);
		renderJson(result);
	}
}