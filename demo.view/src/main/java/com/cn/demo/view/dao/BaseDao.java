package com.cn.demo.view.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface BaseDao<T, K extends Serializable> {

	T get(Class<T> clazz, K key) throws SQLException ;

	boolean saveOrUpdate(T t, K key);

	boolean delete(T t);

//	T get(String key)throws SQLException ;
	
	List<T> getList(Class<T> clazz, ConcurrentHashMap<String,Object> map);

}
