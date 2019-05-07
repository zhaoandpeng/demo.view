package com.cn.demo.view.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.cn.demo.view.utils.PageHelper;

public interface BaseService<T, K extends Serializable> {

	
	public abstract T get(Class<T> clazz, K key) throws SQLException; //根据主键查询实体类
	
	
	public abstract boolean saveOrUpdate(T t, K eventType);
	
	
	public abstract boolean delete(T t);
	
	
	public abstract List<T> getList(Class<T> clazz,ConcurrentHashMap<String,Object> map);
	
	
	public abstract PageHelper<T> getListObjectPage(Class<T> clazz, ConcurrentHashMap<String, Object> map, PageHelper<T> pageModel );
	
	
	public abstract int deleteBatch(final List<T> list, ConcurrentHashMap<String,Object> map);
	
	
 }
