package com.cn.demo.view.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.demo.view.model.BaseMenu;
import com.cn.demo.view.service.BaseMenuService;

@Controller
@RequestMapping("/api/v1/sys/menu")
public class BaseMenuController extends BaseController{

	@Resource
	private BaseMenuService baseMenuService;
	
	@ResponseBody
	@RequestMapping("/index/data")
	public String index_view() {
		
		List<BaseMenu> list = baseMenuService.getList(BaseMenu.class, null);
		
		for (BaseMenu baseMenu : list) {
			
		}
		
		
		return toJson(list, list.size());
	}
	
	
}
