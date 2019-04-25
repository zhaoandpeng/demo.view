package com.cn.demo.view.dao.impl;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
		try {
			this.new  SqlUtils().getSaveSql(t);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Jdbc
//		StringBuffer buffer = new StringBuffer("insert into "+t.getClass().getAnnotation(TableInfoAnnotation.class).tableName()+ "() ");
//		jdbcTemplate.update(sql, pss)
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

	
	
	/*
	 * private SqlEntity getSaveSql(T t) throws Exception{ SqlEntity sqlEntity = new
	 * SqlEntity(); sqlEntity.setParams(new ArrayList<Object>()); Field[] fields =
	 * entityClass.getDeclaredFields(); StringBuffer sql = new StringBuffer("");
	 * sql.append("insert into ").append(StrUtils.changeName(entityClass.
	 * getSimpleName())).append(" ( "); int paramLength = 0; for (Field field :
	 * fields) { StringBuffer methodName = new StringBuffer(""); if(field.getType()
	 * == boolean.class){ if(field.getName().contains("is")){
	 * methodName.append(field.getName()); }else{
	 * methodName.append("is").append(StrUtils.firstCodeToUpperCase(field.getName())
	 * ); } }else{
	 * methodName.append("get").append(StrUtils.firstCodeToUpperCase(field.getName()
	 * )); } Method method = entityClass.getMethod(methodName.toString(), new
	 * Class[]{}); Object value = method.invoke(t, new Object[]{});
	 * if(!StrUtils.isEmpty(value)){ if(value instanceof Enum){
	 * sqlEntity.getParams().add(((Enum) value).ordinal()); }else{
	 * sqlEntity.getParams().add(value); }
	 * sql.append("`").append(StrUtils.changeName(field.getName())).append("`").
	 * append(","); paramLength ++; } } if(sql.indexOf(",") > -1){
	 * sql.deleteCharAt(sql.length() - 1); } sql.append(") values("); for (int
	 * i=0;i<paramLength;i++) { sql.append("?,"); } if(sql.indexOf(",") > -1){
	 * sql.deleteCharAt(sql.length() - 1); } sql.append(")");
	 * //System.out.println("sql.toString()="+sql.toString());
	 * sqlEntity.setSql(sql.toString()); return sqlEntity; }
	 */
	
	class SqlUtils {

		private String sql;
		
		private Object[] obj;
		
		public  SqlUtils  getSaveSql(T t) throws InstantiationException, IllegalAccessException {
			
			Class<? extends Object> clazz = t.getClass();
			
			Field[] fields = clazz.getDeclaredFields();
			
			for (Field field : fields) {
				
				System.out.println(field.getName()+"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			}
			
			
			System.out.println();
			
			
			return null;
		}
	}
}
