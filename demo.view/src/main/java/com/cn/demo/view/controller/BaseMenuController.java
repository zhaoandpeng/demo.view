package com.cn.demo.view.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.demo.view.model.BaseMenu;
import com.cn.demo.view.model.BaseUser;
import com.cn.demo.view.service.BaseMenuService;
import com.cn.demo.view.utils.EventType;
import com.cn.demo.view.utils.PageHelper;

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
		
		String pageNo = getRequest().getParameter("page");
		
		String pageSize = getRequest().getParameter("limit");
		
		PageHelper<BaseMenu> page = new PageHelper<BaseMenu>();
		
		page.setPageNo(Integer.parseInt(pageNo));
		
		page.setPageSize(Integer.parseInt(pageSize));
		
		ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<>();
		
		page = baseMenuService.getListObjectPage(BaseMenu.class, map, page);
		
		return toJson(page.getResult(),(int)page.getTotalCount());
	}
	
	
	@ResponseBody
	@RequestMapping("/index/data")
	public String index_data() {
		
		List<BaseMenu> userResource = getCurrentBaseUser().getRoleResources();
		
		List<String> menusListId = new ArrayList<String>();
		
		userResource.stream().forEach(model -> menusListId.add(model.getID()));
		
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
				
				node.put("id", model.getID());
				
				node.put("pId", model.getPID());
				
				node.put("name", model.getMENU_NAME());
				
				if(menusListId.contains(model.getID())) {
					
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
		String id = getRequest().getParameter("ID");
		String menuName = getRequest().getParameter("MENU_NAME");
		String menuUrl = getRequest().getParameter("MENU_URL");
		String menuIcon = getRequest().getParameter("MENU_ICON");
		String pid = getRequest().getParameter("PID");
		String orderNo = getRequest().getParameter("ORDER_NO");
		if(!StringUtil.isNullOrEmpty(id)) {
			BaseMenu model = baseMenuService.get(BaseMenu.class, id);
			if(null!=model) {
				model.setPID(pid);
				model.setMENU_NAME(menuName);
				model.setMENU_URL(menuUrl);
				model.setMENU_ICON(menuIcon);
				model.setORDER_NO(Integer.parseInt(orderNo));
				global = this.baseMenuService.saveOrUpdate(model,EventType.EVENT_UPDATE);//执行更新操作
			}
		}else {
			BaseUser user = getCurrentBaseUser();
			BaseMenu model = new BaseMenu();
			model.setID(UUID.randomUUID().toString());
			model.setPID(pid);
			model.setMENU_NAME(menuName);
			model.setMENU_URL(menuUrl);
			model.setMENU_ICON(menuIcon);
			model.setORDER_NO(Integer.parseInt(orderNo));
			model.setCREATE_DATE(new Date());
			model.setCREATOR_ID(user.getId());
			model.setCREATOR_NAME(user.getUsername());
			global = this.baseMenuService.saveOrUpdate(model,EventType.EVENT_ADD);//执行新增操作
		}
		resultMap.put("status", global);
		return toJson(resultMap);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") String id) throws SQLException {
		
		ConcurrentHashMap<String,Object> resultMap = new ConcurrentHashMap<>();
		
		if(!StringUtil.isNullOrEmpty(id)) {
			
			String[] arr = id.split(",");
			
			for (String primary : arr) {
				
				BaseMenu BaseMenu = this.baseMenuService.get(BaseMenu.class, primary);
				
				if(null!=BaseMenu) {
					
					boolean delete = this.baseMenuService.delete(BaseMenu);
					
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
