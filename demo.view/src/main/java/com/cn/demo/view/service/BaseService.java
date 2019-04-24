package com.cn.demo.view.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface BaseService<T, K extends Serializable> {

	
	public abstract T get(Class<T> clazz, K key) throws SQLException; //根据主键查询实体类
	
	
	public abstract boolean saveOrUpdate(T t);
	
	
	public abstract boolean delete(T t);
	
	
	public abstract List<T> getList(Class<T> clazz,K value, String column);
 }
