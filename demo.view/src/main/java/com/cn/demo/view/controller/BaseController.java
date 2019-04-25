package com.cn.demo.view.controller;

import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import net.sf.json.JSONObject;

public class BaseController {

	public HttpServletRequest getRequest() {
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		return request;
	}
	
	
	public String toJson(Object object, int count) {
		
		ConcurrentHashMap<String,Object> dataMap = new ConcurrentHashMap<String,Object>();
		
		dataMap.put("data", object);
		
		dataMap.put("count", count);
		
		dataMap.put("code", 0);
		
//		dataMap.put("msg", null);
		
		return JSONObject.fromObject(dataMap).toString();
		
	}
	
	public String toJson(Object object) {
		
		ConcurrentHashMap<String,Object> dataMap = new ConcurrentHashMap<String,Object>();
		
		dataMap.put("data", object);
		
		return JSONObject.fromObject(dataMap).toString();
		
	}
	
}
