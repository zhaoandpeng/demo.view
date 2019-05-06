package com.cn.demo.view.controller;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cn.demo.view.model.BaseUser;
import com.cn.demo.view.utils.JsonDateValueProcessor;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class BaseController {

	public HttpServletRequest getRequest() {
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		return request;
	}
	
	
	public String toJson(Object object, int count) {
		
		ConcurrentHashMap<String,Object> dataMap = new ConcurrentHashMap<String,Object>();
		
		JsonConfig jsonConfig = new JsonConfig();
		
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		
		if(null==object) { 
			
			dataMap.put("data", ""); 
		}else { 
			
			dataMap.put("data", object);
		}
		
		dataMap.put("count", count);
		
		dataMap.put("code", 0);
		
		return JSONObject.fromObject(dataMap,jsonConfig).toString();
		
	}
	
	public String toJson(Object object) {
		
		ConcurrentHashMap<String,Object> dataMap = new ConcurrentHashMap<String,Object>();
		
		JsonConfig jsonConfig = new JsonConfig();
		
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		
		dataMap.put("data", object);
		
		return JSONObject.fromObject(dataMap,jsonConfig).toString();
		
	}
	
	public BaseUser getCurrentBaseUser() {
		
		BaseUser user = (BaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		return user;
	}
	
}
