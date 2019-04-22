package com.cn.demo.view.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.cn.demo.view.dao.BaseUserDao;
import com.cn.demo.view.model.BaseUser;
@Component
public class BaseUserDaoImpl extends BaseDaoImpl<BaseUser,java.lang.String> implements BaseUserDao{

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public BaseUser getBaseUserByParam(String username) {
		
		StringBuffer buffer = new StringBuffer(" select * from base_user where username = '"+username+"' ");
		List<BaseUser> dataList = jdbcTemplate.query(buffer.toString(), new Object[]{}, new BeanPropertyRowMapper<BaseUser>(BaseUser.class));
		if(dataList.isEmpty()) {return null;}
		return dataList.get(0);
		
	}


}
