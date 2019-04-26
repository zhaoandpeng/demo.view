package com.cn.demo.view.dao.impl;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.cn.demo.view.annotation.TableInfoAnnotation;
import com.cn.demo.view.dao.BaseDao;
import com.cn.demo.view.model.SqlEntry;
@Component
public class BaseDaoImpl<T, K> implements BaseDao<T, java.lang.String> {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean saveOrUpdate(T t) {
		SqlEntry entry = null;
		try {
			entry = getSaveSql(t);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		int count = jdbcTemplate.update(entry.getSql(), entry.getObj());
		return count==1? true:false;
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

	@Override
	public List<T> getList(Class<T> clazz, ConcurrentHashMap<String,Object> map) {
		StringBuffer buffer = new StringBuffer("select * from "+clazz.getAnnotation(TableInfoAnnotation.class).tableName()+" where 1 = 1 ");
		if(null!= map&&!map.isEmpty()) {
			for (Map.Entry<String, Object>  entry  : map.entrySet()) {
				buffer.append(" and "+entry.getKey()+"='"+entry.getValue().toString()+"'");
			}
		}
		List<T> dataList = jdbcTemplate.query(buffer.toString(), new Object[]{}, new BeanPropertyRowMapper<T>(clazz));
		if(dataList.isEmpty()) {return null;}
		return dataList;
	}

	private  synchronized  SqlEntry  getSaveSql(T t) throws InstantiationException, IllegalAccessException {
		
		SqlEntry entry = new SqlEntry();
		
		StringBuffer buffer = new StringBuffer("insert into "); 
		
		Class<? extends Object> clazz = t.getClass();
		
		buffer.append(""+clazz.getAnnotation(TableInfoAnnotation.class).tableName()+" values(");
		
		Field[] fields = clazz.getDeclaredFields();
		
		List<Object> listField = new ArrayList<>();
		
		int i = 0;
		
		for (Field field : fields) {
			
			i++;
			
			field.setAccessible(true);
			
			if(field.getName().indexOf("serialVersionUID")!=-1) {
				
				continue;
			}
			
			System.out.println(field.getName()+"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			
			Object val = new Object();
			
			val = field.get(t);
			
			if(fields.length==i) {
				buffer.append("?");
			}else {
				buffer.append("?,");
			}
			
			listField.add(val);
		}
		buffer.append(")");
		
		entry.setSql(buffer.toString());
		
		entry.setObj(listField.toArray(new Object[listField.size()]));
		
		return entry;
	}
}
