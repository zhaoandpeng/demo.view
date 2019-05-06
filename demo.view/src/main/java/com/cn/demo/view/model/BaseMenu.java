package com.cn.demo.view.model;

import java.io.Serializable;
import java.util.Date;

import com.cn.demo.view.annotation.TableInfoAnnotation;

/**
 * 
 * 
 * @author mr.zhao
 * 
 * @date 2019-04-23
 */
@TableInfoAnnotation(tableName = "base_menu", primaryKey = "ID")
public class BaseMenu implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -2077714120763534169L;

    private String ID; //主键

    private String PID;//父级主键

    private String MENU_NAME;//菜单名称

    private String MENU_URL;//菜单链接

    private String MENU_ICON;//菜单图标

    private Integer ORDER_NO;//排序号

    private String CREATOR_ID;//创建人主键

    private String CREATOR_NAME;//创建人名称

    private Date CREATE_DATE;//创建日期

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID == null ? null : ID.trim();
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID == null ? null : PID.trim();
    }

    public String getMENU_NAME() {
        return MENU_NAME;
    }

    public void setMENU_NAME(String MENU_NAME) {
        this.MENU_NAME = MENU_NAME == null ? null : MENU_NAME.trim();
    }

    public String getMENU_URL() {
        return MENU_URL;
    }

    public void setMENU_URL(String MENU_URL) {
        this.MENU_URL = MENU_URL == null ? null : MENU_URL.trim();
    }

    public String getMENU_ICON() {
        return MENU_ICON;
    }

    public void setMENU_ICON(String MENU_ICON) {
        this.MENU_ICON = MENU_ICON == null ? null : MENU_ICON.trim();
    }

    public Integer getORDER_NO() {
        return ORDER_NO;
    }

    public void setORDER_NO(Integer ORDER_NO) {
        this.ORDER_NO = ORDER_NO;
    }

    public String getCREATOR_ID() {
        return CREATOR_ID;
    }

    public void setCREATOR_ID(String CREATOR_ID) {
        this.CREATOR_ID = CREATOR_ID == null ? null : CREATOR_ID.trim();
    }

    public String getCREATOR_NAME() {
        return CREATOR_NAME;
    }

    public void setCREATOR_NAME(String CREATOR_NAME) {
        this.CREATOR_NAME = CREATOR_NAME == null ? null : CREATOR_NAME.trim();
    }

    public Date getCREATE_DATE() {
        return CREATE_DATE;
    }

    public void setCREATE_DATE(Date CREATE_DATE) {
        this.CREATE_DATE = CREATE_DATE;
    }
}