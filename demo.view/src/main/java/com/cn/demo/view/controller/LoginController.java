package com.cn.demo.view.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.demo.view.service.BaseUserService;

@Controller
public class LoginController{

	
	@Resource
	private BaseUserService baseUserService;
	
	@RequestMapping(value="/main") 
	public String home() {
		
		return "main/main";
	}
	 
	
}
