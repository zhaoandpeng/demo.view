package com.cn.demo.view.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.cn.demo.view.utils.PageHelper;

public interface BaseDao<T, K extends Serializable> {

	T get(Class<T> clazz, K key) throws SQLException ;

	boolean saveOrUpdate(T t, K key);

	boolean delete(T t);

	List<T> getList(Class<T> clazz, ConcurrentHashMap<String,Object> map);
	
	PageHelper<T> getListPage(Class<T> clazz, ConcurrentHashMap<String,Object> map);
	
//	PageHelper<ConcurrentHashMap<String,Object>> getListPage(ConcurrentHashMap<String,Object> map, );

}
