package com.cn.demo.view.controller;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cn.demo.view.model.BaseRole;
import com.cn.demo.view.service.BaseRoleService;
import com.cn.demo.view.utils.LayTableUtils;

import io.netty.util.internal.StringUtil;

@Controller
@RequestMapping("/api/v1/sys/role")
public class BaseRoleController extends BaseController{

	
	@Resource
	private BaseRoleService baseRoleService;
	
	
	@RequestMapping(value = "/index")
	public String index() {
		
		return "system/role/index";
	}
	
	@ResponseBody
	@RequestMapping("/index/data")
	public String index_view() {
		
		List<BaseRole> list = baseRoleService.getList(BaseRole.class, null);
		
		return toJson(list, list.size());
	}
	
	@ResponseBody
	@RequestMapping("/add_or_update")
	public String add_or_update() {
		
		ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<>();
		
		String roleName = getRequest().getParameter("roleName");
		
		String roleId = getRequest().getParameter("id");
		
		if(!StringUtil.isNullOrEmpty(roleName)) {
			
			map.put("roleName", roleName);
			
			List<BaseRole> list = baseRoleService.getList(BaseRole.class, map);
			
			if(null==list) {
				
				BaseRole model = new BaseRole();
				
				model.setRoleName(roleName);
				
				boolean saveOrUpdate = this.baseRoleService.saveOrUpdate(model);//执行保存操作
				
				map.clear(); 
				
				map.put("status", saveOrUpdate);
			}
		}else {
			
			map.put("status", false);
			
			map.put("message", "参数为空,请检查所传参数");
		}
		
		return toJson(map);
	}
}
