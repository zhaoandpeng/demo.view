package com.cn.demo.view.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.demo.view.service.BaseRoleService;

@Controller
public class LoginController{

	
	@Resource
	private BaseRoleService baseRoleService;
	
	@RequestMapping(value="/main") 
	public String home() {
		
		//加载用户树形菜单列表
		
		
		
		
		return "main/main";
	}
	 
	
}
