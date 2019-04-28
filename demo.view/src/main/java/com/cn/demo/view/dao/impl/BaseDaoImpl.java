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
import com.cn.demo.view.utils.EventType;
import com.cn.demo.view.utils.PageHelper;
@Component
public class BaseDaoImpl<T, K> implements BaseDao<T, java.lang.String> {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean saveOrUpdate(T t, String eventType) {
		SqlEntry entry = null;
		try {
			if(EventType.EVENT_ADD.equals(eventType)) {
				entry = getSaveSql(t);
			}
			if(EventType.EVENT_UPDATE.equals(eventType)) {
				entry = getUpdateSql(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		int count = jdbcTemplate.update(entry.getSql(), entry.getObj());
		return count==1? true:false;
	}

	@Override
	public boolean delete(T t) {
		
		
		  SqlEntry entry = null; try { entry = getDeleteSql(t); } catch (Exception e) {
		  e.printStackTrace(); } int count = jdbcTemplate.update(entry.getSql(),
		  entry.getObj());
		  
		  return count==1? true:false;
		 
//		getListPage((Class<T>) BaseRole.class,new ConcurrentHashMap<String,Object>());
		
//		return false;
	}
	
	public int deleteBatch(final List<T> list, ConcurrentHashMap<String,Object> map) {
		
		StringBuffer buffer = new StringBuffer("delete from ");
		
		Class<? extends Object> clazz = list.get(0).getClass();
		
		buffer.append(clazz.getAnnotation(TableInfoAnnotation.class).tableName() + " where 1 = 1 ");
		
		if(null!= map&&!map.isEmpty()) {
			
			for (Map.Entry<String, Object>  entry  : map.entrySet()) {
				
				buffer.append(" and "+entry.getKey()+"='"+entry.getValue().toString()+"'");
			}
		}
		
		int count = jdbcTemplate.update(buffer.toString());
		
//		List<Object> listField = new ArrayList<>();
		
//		
//		Field[] declaredFields = clazz.getDeclaredFields();
//		
//		for (Field field : declaredFields) {
//			
//			if(field.getName().indexOf("serialVersionUID")!=-1) { continue; }
//			
//			buffer.append("and "+field.getName()+"= ?");
//			
//			
//		}
//		int[] batchUpdate = null;
//		int[] batchUpdate = jdbcTemplate.batchUpdate(buffer.toString(), );
		
		return count;
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
			
			if(fields.length==i) { buffer.append("?"); }else { buffer.append("?,"); }
			
			listField.add(val);
		}
		buffer.append(")");
		
		entry.setSql(buffer.toString());
		
		entry.setObj(listField.toArray(new Object[listField.size()]));
		
		return entry;
	}
	
	private  synchronized  SqlEntry  getUpdateSql(T t) throws InstantiationException, IllegalAccessException {
		
		SqlEntry entry = new SqlEntry();
		
		StringBuffer buffer = new StringBuffer("update "); 
		
		Class<? extends Object> clazz = t.getClass();
		
		buffer.append(""+clazz.getAnnotation(TableInfoAnnotation.class).tableName()+" set ");
		
		Field[] fields = clazz.getDeclaredFields();
		
		List<Object> listField = new ArrayList<>();
		
		int i = 0; Object primary = null;
		
		for (Field field : fields) {
			
			i++;
			
			field.setAccessible(true);
			
			if(field.getName().indexOf("serialVersionUID")!=-1) {
				
				continue;
			}
			
			if(field.getName().indexOf(clazz.getAnnotation(TableInfoAnnotation.class).primaryKey())!=-1) {
				
				primary = field.get(t);
				
				continue;
			}
			
			System.out.println(field.getName()+"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			
			Object val = new Object();
			
			val = field.get(t);
			
			if(fields.length==i) { buffer.append(field.getName()+"=?"); }else { buffer.append(field.getName()+"?,"); }
			
			listField.add(val);
		}
		buffer.append(" where ");
		
		buffer.append(""+clazz.getAnnotation(TableInfoAnnotation.class).primaryKey()+" = ?");
		
		entry.setSql(buffer.toString());
		
		listField.add(primary);
		
		entry.setObj(listField.toArray(new Object[listField.size()]));
		
		return entry;
	}
	
	@SuppressWarnings("unused")
	private  synchronized  SqlEntry  getDeleteSql(T t) throws InstantiationException, IllegalAccessException {
		
		SqlEntry entry = new SqlEntry();
		
		StringBuffer buffer = new StringBuffer("delete from "); 
		
		Class<? extends Object> clazz = t.getClass();
		
		buffer.append(""+clazz.getAnnotation(TableInfoAnnotation.class).tableName()+" where ").append(clazz.getAnnotation(TableInfoAnnotation.class).primaryKey()+ "=?" );
		
		Field[] fields = clazz.getDeclaredFields();
		
		Object primary = null;  List<Object> listField = new ArrayList<>();
		
		for (Field field : fields) {
			
			field.setAccessible(true);
			
			if(field.getName().equals(clazz.getAnnotation(TableInfoAnnotation.class).primaryKey())) {
				
				primary = field.get(t);   listField.add(primary);  break;
			}
		}
		entry.setSql(buffer.toString());
		
		entry.setObj(listField.toArray(new Object[listField.size()]));
		
		return entry;
	}
	/*
	 * public static void main(String[] args) { List<String> list = new
	 * ArrayList<>();
	 * 
	 * 
	 * list.add("1"); list.add("2"); list.add("3"); list.add("4"); list.add("5");
	 * 
	 * 
	 * 
	 * }
	 */

	@Override
	public PageHelper<T> getListPage(Class<T> clazz, ConcurrentHashMap<String, Object> map) {
		
		StringBuffer buffer = new StringBuffer("select count(*) from "+clazz.getAnnotation(TableInfoAnnotation.class).tableName()+" where 1 = 1 ");
		
		if(null!= map&&!map.isEmpty()) {
			
			for (Map.Entry<String, Object>  entry  : map.entrySet()) {
				
				buffer.append(" and "+entry.getKey()+"='"+entry.getValue().toString()+"'");
			}
		}
		
		int count = jdbcTemplate.queryForObject(buffer.toString(),new Object[]{}, Integer.class);
		
		if(0==count) {
			
			return new PageHelper<>();
		}
		return null;
	}
}
