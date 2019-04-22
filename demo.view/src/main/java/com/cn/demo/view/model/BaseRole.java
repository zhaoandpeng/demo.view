package com.cn.demo.view.model;

import java.io.Serializable;

import com.cn.demo.view.annotation.TableInfoAnnotation;

@TableInfoAnnotation(tableName = "base_role", primaryKey = "id")
public class BaseRole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1332067334201646856L;

	private String id;
	
	private String roleName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
