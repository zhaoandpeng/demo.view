package com.cn.demo.view.controller;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.demo.view.model.BaseRoleResources;
import com.cn.demo.view.service.BaseRoleResourceService;
import com.cn.demo.view.utils.EventType;

import io.netty.util.internal.StringUtil;

@Controller
@RequestMapping("/api/v1/sys/role/resources")
public class BaseRoleResourceController extends BaseController{

	@Resource
	private BaseRoleResourceService baseRoleResourceService;
	
	@ResponseBody
	@RequestMapping("/update")
	public String update() {
		
		boolean saveOrUpdate = false;
		
		ConcurrentHashMap<String,Object> resultMap = new ConcurrentHashMap<>();
		
		ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<>();
		
		String roleId = getRequest().getParameter("roleId");
		 
		String menus = getRequest().getParameter("menus");
		
		if(StringUtil.isNullOrEmpty(roleId)) {
			
			resultMap.put("status", false); resultMap.put("message", "参数为空,请检查所传参数");  return toJson(resultMap); 
		}
		if(!StringUtil.isNullOrEmpty(roleId)&&!StringUtil.isNullOrEmpty(menus)) {
			
			map.put("role_id", roleId);
			
			List<BaseRoleResources> listModel = baseRoleResourceService.getList(BaseRoleResources.class, map);
			
			if(null!=listModel) {
				
				int deleteCount = baseRoleResourceService.deleteBatch(listModel,map);
				
				if(deleteCount == listModel.size()) {
					
					String[] menu = menus.split(",");
				
					for (String menuId : menu) {
						
						BaseRoleResources model = new BaseRoleResources();
						
						model.setRoleId(roleId);
						
						model.setMenuId(menuId);
						
						saveOrUpdate = baseRoleResourceService.saveOrUpdate(model, EventType.EVENT_ADD);
					}
					
				}else {
					
					resultMap.put("status", false); resultMap.put("message", "权限分配异常");
				}
				
			}
		}
		resultMap.put("status", saveOrUpdate);
		
		return toJson(resultMap);
	}
}
