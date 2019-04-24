package com.cn.demo.view.utils;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class LayTableUtils {

	public static String toJson(Object object) {
		
		Map<String,Object> dataMap = new HashMap<String,Object>();
		
		dataMap.put("data", object);
		
		dataMap.put("code", 0);
		
		dataMap.put("msg", null);
		
		return JSONObject.fromObject(dataMap).toString();
	}
	
}
