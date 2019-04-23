package com.cn.demo.view.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface BaseDao<T, K extends Serializable> {

	T get(Class<T> clazz, K key) throws SQLException ;

	boolean saveOrUpdate(T t);

	boolean delete(T t);

//	T get(String key)throws SQLException ;
	
	List<T> getList(Class<T> clazz,K key, String column);

}
