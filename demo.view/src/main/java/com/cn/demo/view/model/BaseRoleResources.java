package com.cn.demo.view.model;

import java.io.Serializable;

import com.cn.demo.view.annotation.TableInfoAnnotation;

/**
 * 
 * 
 * @author MR.ZHAO
 * 
 * @date 2019-04-24
 */
@TableInfoAnnotation(tableName = "BASE_ROLE_RESOURCES", primaryKey = "roleId")
public class BaseRoleResources implements Serializable{
   
	
	private static final long serialVersionUID = 7374947625469504034L;

	private String roleId;

    private String menuId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }
}