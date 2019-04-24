package com.cn.demo.view.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.demo.view.model.BaseRole;
import com.cn.demo.view.service.BaseRoleService;
import com.cn.demo.view.utils.LayTableUtils;

@Controller
@RequestMapping("/api/v1/sys/role")
public class BaseRoleController {

	
	@Resource
	private BaseRoleService baseRoleService;
	
	
	@RequestMapping(value = "/index")
	public String index() {
		
		return "system/role/index";
	}
	
	@ResponseBody
	@RequestMapping("/index/data")
	public String index_view() {
		
		List<BaseRole> list = baseRoleService.getList(BaseRole.class, "1", "1");
		
		return LayTableUtils.toJson(list);
	}
}
