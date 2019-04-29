package com.cn.demo.view.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.demo.view.model.BaseUser;
import com.cn.demo.view.service.BaseRoleService;

@Controller
public class LoginController extends BaseController{

	
	@Resource
	private BaseRoleService baseRoleService;
	
	@RequestMapping(value="/main") 
	public String home(Model model) {
		
		
		return "main/main";
	}
	
	@ResponseBody
	@RequestMapping(value="/main/data")
	public BaseUser home() {
		
		BaseUser user = getCurrentBaseUser();
		
		return user;
	}
	
	
	 
	
}
