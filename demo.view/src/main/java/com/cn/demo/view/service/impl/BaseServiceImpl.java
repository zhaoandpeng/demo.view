package com.cn.demo.view.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.demo.view.dao.BaseDao;
import com.cn.demo.view.service.BaseService;
@Service
public class BaseServiceImpl<T, K> implements BaseService<T,java.lang.String>{

	
	@Resource(name="baseDaoImpl")
	private BaseDao<T,java.lang.String> baseDao;
	
	
	
	@Override
	public T get(Class<T> clazz, String key) throws SQLException {
		
		return (T) baseDao.get(clazz, key);
	}

	@Override
	public boolean saveOrUpdate(T t, String eventType) {
		
		return baseDao.saveOrUpdate(t, eventType);
	}

	@Override
	public boolean delete(T t) {
		
		return baseDao.delete(t);
	}

	@Override
	public List<T> getList(Class<T> clazz, ConcurrentHashMap<String,Object> map) {
		
		return baseDao.getList(clazz, map);
	}

	@Override
	public int deleteBatch(List<T> list,ConcurrentHashMap<String,Object> map) {
	
		return baseDao.deleteBatch(list,map);
	}

}
