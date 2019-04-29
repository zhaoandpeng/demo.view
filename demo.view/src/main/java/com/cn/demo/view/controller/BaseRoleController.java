package com.cn.demo.view.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cn.demo.view.model.BaseRole;
import com.cn.demo.view.service.BaseRoleService;
import com.cn.demo.view.utils.EventType;

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
	@RequestMapping(value = "/index/data")
	public String index_view() {
		
		List<BaseRole> list = baseRoleService.getList(BaseRole.class, null);
		
		return toJson(list, list.size());
	}
	
	@ResponseBody
	@RequestMapping(value = "/add_or_update")
	public String add_or_update() throws SQLException {
		
		ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<>();
		
		ConcurrentHashMap<String,Object> resultMap = new ConcurrentHashMap<>();
		
		String roleName = getRequest().getParameter("roleName");
		
		String roleId = getRequest().getParameter("id");
		
		if(!StringUtil.isNullOrEmpty(roleName)&&StringUtil.isNullOrEmpty(roleId)) {
			
			map.put("roleName", roleName);
			
			List<BaseRole> list = baseRoleService.getList(BaseRole.class, map);
			
			if(null==list) {
				
				BaseRole model = new BaseRole();
				
				model.setRoleName(roleName);
				
				model.setId(UUID.randomUUID().toString());
				
				boolean saveOrUpdate = this.baseRoleService.saveOrUpdate(model,EventType.EVENT_ADD);//执行保存操作
				
				map.clear(); 
				
				resultMap.put("status", saveOrUpdate);
			}
		}
		if(!StringUtil.isNullOrEmpty(roleName)&&!StringUtil.isNullOrEmpty(roleId)) {
			
			map.put("roleName", roleName);	
			
			List<BaseRole> list = baseRoleService.getList(BaseRole.class, map); //保证更新時不存在该角色名称
			
			if(null==list) {
				
				map.clear();  map.put("id", roleId);
				
				BaseRole model = baseRoleService.get(BaseRole.class, roleId);
				
				if(null!=model) {
					
					model.setRoleName(roleName);
					
					boolean saveOrUpdate = this.baseRoleService.saveOrUpdate(model,EventType.EVENT_UPDATE);//执行修改操作
					
					resultMap.put("status", saveOrUpdate);
				}
			}else {
				
				resultMap.put("status", false);
				
				resultMap.put("message", "该角色名称已存在");
			}
		}
		if(StringUtil.isNullOrEmpty(roleName)&&StringUtil.isNullOrEmpty(roleId)) {
			
			resultMap.put("status", false); resultMap.put("message", "参数为空,请检查所传参数");
		}

		return toJson(resultMap);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") String id) throws SQLException {
		
		ConcurrentHashMap<String,Object> resultMap = new ConcurrentHashMap<>();
		
		if(!StringUtil.isNullOrEmpty(id)) {
			
			String[] arr = id.split(",");
			
			for (String primary : arr) {
				
				BaseRole baseRole = this.baseRoleService.get(BaseRole.class, primary);
				
				if(null!=baseRole) {
					
					boolean delete = this.baseRoleService.delete(baseRole);
					
					resultMap.put("status", delete);
				}
			}
		}
		
		if(StringUtil.isNullOrEmpty(id)) {
			
			resultMap.put("status", false); resultMap.put("message", "参数为空,请检查所传参数");
		}

		return toJson(resultMap);
	}
}
