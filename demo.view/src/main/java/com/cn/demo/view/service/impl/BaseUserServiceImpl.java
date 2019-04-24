package com.cn.demo.view.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.cn.demo.view.dao.BaseMenuDao;
import com.cn.demo.view.dao.BaseRoleDao;
import com.cn.demo.view.dao.BaseRoleResourcesDao;
import com.cn.demo.view.dao.BaseUserDao;
import com.cn.demo.view.dao.BaseUserRoleDao;
import com.cn.demo.view.model.BaseMenu;
import com.cn.demo.view.model.BaseRole;
import com.cn.demo.view.model.BaseRoleResources;
import com.cn.demo.view.model.BaseUser;
import com.cn.demo.view.model.BaseUserRole;
import com.cn.demo.view.service.BaseUserService;

@Service
public class BaseUserServiceImpl extends BaseServiceImpl<BaseUser,java.lang.String> implements BaseUserService, UserDetailsService{
	
	@Resource
	private BaseUserDao baseUserDao;
	
	@Resource
	private BaseUserRoleDao baseUserRoleDao;
	
	@Resource
	private BaseRoleDao baseRoleDao;
	
	@Resource
	private BaseMenuDao baseMenuDao;
	
	@Resource
	private BaseRoleResourcesDao baseRoleResourcesDao;
	
	@Resource 
	private PasswordEncoder passwordEncoder;
	 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		BaseUser baseUser = null;
		
		baseUser = this.baseUserDao.getBaseUserByParam(username);
		
		Assert.notNull(baseUser, "找不到该账户信息！");
		
		//查询用户角色
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		
		List<BaseMenu> listResoure = new ArrayList<BaseMenu>();
		
		List<BaseUserRole> listRole = this.baseUserRoleDao.getList(BaseUserRole.class, baseUser.getId(), "USERID");
		
		if(!listRole.isEmpty()) {
		
			for (BaseUserRole role: listRole) {
				
				String roleName = null;
				
				try {
					
					roleName = this.baseRoleDao.get(BaseRole.class, role.getRoleid()).getRoleName();
					
					List<BaseRoleResources> roleResoure = this.baseRoleResourcesDao.getList(BaseRoleResources.class, role.getRoleid(), "ROLE_ID");
					
					for (BaseRoleResources baseRoleResources : roleResoure) {
						
						BaseMenu baseMenu = this.baseMenuDao.get(BaseMenu.class, baseRoleResources.getMenuId());
						
						listResoure.add(baseMenu);
					}
					
				
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
				list.add(new SimpleGrantedAuthority("ROLE_"+roleName));
			}
			
			baseUser.setRole(list);
			
			baseUser.setRoleResources(listResoure);
		}
		
		baseUser.setPassword(passwordEncoder.encode(baseUser.getPassword()));
		
		return baseUser;
	}


}
