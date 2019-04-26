package com.cn.demo.view.model;

import java.io.Serializable;

public class SqlEntry implements Serializable{

	private static final long serialVersionUID = 6984846443728236566L;

	private String sql;
	
	private Object[] obj ;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public Object[] getObj() {
		return obj;
	}

	public void setObj(Object[] obj) {
		this.obj = obj;
	}
	
}
