package com.cn.demo.view.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.cn.demo.view.annotation.TableInfoAnnotation;
import com.cn.demo.view.dao.BaseDao;
@Component
public class BaseDaoImpl<T, K> implements BaseDao<T, java.lang.String> {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean saveOrUpdate(T t) {
		
		return false;
	}

	@Override
	public boolean delete(T t) {
		
		return false;
	}

	@Override
	public T get(Class<T> clazz, String key) throws SQLException {
		StringBuffer buffer = new StringBuffer("select * from "+clazz.getAnnotation(TableInfoAnnotation.class).tableName()+" where id = '"+key+"'");
		List<T> dataList = jdbcTemplate.query(buffer.toString(), new Object[]{}, new BeanPropertyRowMapper<T>(clazz));
		if(dataList.isEmpty()) {return null;}
		return dataList.get(0);
	}

}
