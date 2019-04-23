package com.cn.demo.view.model;

import java.io.Serializable;

import com.cn.demo.view.annotation.TableInfoAnnotation;

/**
 * 基础用户角色表
 * 
 * @author mr.zhao
 * 
 * @date 2019-04-23
 */
@TableInfoAnnotation(tableName = "base_user_role", primaryKey = "userid")
public class BaseUserRole implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8049033172220209622L;

	/**
     * 用户表主键
     */
    private String userid;

    /**
     * 角色表主键
     */
    private String roleid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }
}