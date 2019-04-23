package com.cn.demo.view.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cn.demo.view.dao.BaseRoleDao;
import com.cn.demo.view.dao.BaseUserDao;
import com.cn.demo.view.model.BaseRole;
import com.cn.demo.view.model.BaseUser;
import com.cn.demo.view.service.BaseUserService;

@Service
public class BaseUserServiceImpl extends BaseServiceImpl<BaseUser,java.lang.String> implements BaseUserService, UserDetailsService{
	
	@Resource
	private BaseUserDao baseUserDao;
	
	@Resource
	private BaseRoleDao baseRoleDao;
	
	@Resource 
	private PasswordEncoder passwordEncoder;
	 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		BaseUser baseUser = null;
		
		baseUser = this.baseUserDao.getBaseUserByParam(username);
		
		if(null!=baseUser) {
			
			//查询用户角色
			List<BaseRole> list = this.baseRoleDao.getList(BaseRole.class, baseUser.getId(), "USERID");
			
			if(!list.isEmpty()) {
				
//				baseUser.getAuthorities()
			}
		}
		
		baseUser.setPassword(passwordEncoder.encode(baseUser.getPassword()));
		
		return baseUser;
	}


}
