package com.cn.demo.view.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.demo.view.model.BaseMenu;
import com.cn.demo.view.model.BaseUser;
import com.cn.demo.view.service.BaseMenuService;
import com.cn.demo.view.utils.EventType;

import io.netty.util.internal.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/api/v1/sys/menu")
public class BaseMenuController extends BaseController{

	@Resource
	private BaseMenuService baseMenuService;
	
	
	@RequestMapping(value = "/index")
	public String index() {
		
		return "system/menu/index";
	}
	
	@ResponseBody
	@RequestMapping("/index/view")
	public String index_view() {
		
		List<BaseMenu> list = baseMenuService.getList(BaseMenu.class, null);
		
		return toJson(list,list.size());
	}
	
	
	@ResponseBody
	@RequestMapping("/index/data")
	public String index_data() {
		
		List<BaseMenu> userResource = getCurrentBaseUser().getRoleResources();
		
		List<String> menusListId = new ArrayList<String>();
		
		userResource.stream().forEach(model -> menusListId.add(model.getId()));
		
		List<BaseMenu> list = baseMenuService.getList(BaseMenu.class, null);
		
		JSONArray ztree = new JSONArray();  JSONObject rootNode = new JSONObject();
		
		rootNode.put("id", "0");
	    
		rootNode.put("pId", null);
	    
		rootNode.put("name", "权限树");
	    
		rootNode.put("type", "root");
		
		rootNode.put("icon", "/css/img/diy/1_open.png");
		
		ztree.add(rootNode);
		
		if(null!=list) {
			
			for (BaseMenu model : list) {
				
				JSONObject node = new JSONObject();
				
				node.put("id", model.getId());
				
				node.put("pId", model.getPid());
				
				node.put("name", model.getMenuName());
				
				if(menusListId.contains(model.getId())) {
					
					node.put("checked", true);
				}
				
				
				ztree.add(node);
			}
		}
		return toJson(ztree);
	}
	
	@ResponseBody
	@RequestMapping(value = "/add_or_update")
	public String add_or_update() throws SQLException {
		boolean global = false;
		ConcurrentHashMap<String,Object> resultMap = new ConcurrentHashMap<>();
		String id = getRequest().getParameter("id");
		String menuName = getRequest().getParameter("menuName");
		String menuUrl = getRequest().getParameter("menuUrl");
		String menuIcon = getRequest().getParameter("menuIcon");
		String pid = getRequest().getParameter("pid");
		String orderNo = getRequest().getParameter("orderNo");
		if(!StringUtil.isNullOrEmpty(id)) {
			BaseMenu model = baseMenuService.get(BaseMenu.class, id);
			if(null!=model) {
				model.setPid(pid);
				model.setMenuName(menuName);
				model.setMenuUrl(menuUrl);
				model.setMenuIcon(menuIcon);
				model.setOrderNo(Integer.parseInt(orderNo));
				global = this.baseMenuService.saveOrUpdate(model,EventType.EVENT_UPDATE);//执行更新操作
			}
		}else {
			BaseUser user = getCurrentBaseUser();
			BaseMenu model = new BaseMenu();
			model.setId(UUID.randomUUID().toString());
			model.setPid(pid);
			model.setMenuName(menuName);
			model.setMenuUrl(menuUrl);
			model.setMenuIcon(menuIcon);
			model.setOrderNo(Integer.parseInt(orderNo));
			model.setCreateDate(new Date());
			model.setCreatorId(user.getId());
			model.setCreatorName(user.getUsername());
			global = this.baseMenuService.saveOrUpdate(model,EventType.EVENT_ADD);//执行新增操作
		}
		resultMap.put("status", global);
		return toJson(resultMap);
	}
	
}
