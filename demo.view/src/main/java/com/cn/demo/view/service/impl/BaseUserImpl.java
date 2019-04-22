package com.cn.demo.view.service.impl;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cn.demo.view.dao.BaseUserDao;
import com.cn.demo.view.model.BaseUser;
import com.cn.demo.view.service.BaseUserService;

@Service
public class BaseUserImpl extends BaseServiceImpl<BaseUser,java.lang.String> implements BaseUserService, UserDetailsService{
	
	
	
	@Resource
	private BaseUserDao baseUserDao;
	
	
	@Resource 
	private PasswordEncoder passwordEncoder;
	 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		BaseUser baseUser = null;
		
		baseUser = this.baseUserDao.getBaseUserByParam(username);
		
		if(null==baseUser) {
			
			//自定义提示查无此用户
		}
		
		baseUser.setPassword(passwordEncoder.encode(baseUser.getPassword()));
		
		return baseUser;
	}


}
