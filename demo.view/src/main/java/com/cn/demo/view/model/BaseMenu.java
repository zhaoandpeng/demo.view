package com.cn.demo.view.model;

import java.io.Serializable;
import java.util.Date;

import com.cn.demo.view.annotation.TableInfoAnnotation;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2019-04-23
 */
@TableInfoAnnotation(tableName = "base_menu", primaryKey = "id")
public class BaseMenu implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -2077714120763534169L;

	private String id;  //主键

    private String pid; //父级主键

    private String menuName; //菜单名称

    private String menuUrl; //菜单链接

    private String menuIcon; //菜单图标

    private Integer orderNo; //排序号

    private String creatorId; //创建人主键

    private String creatorName; //创建人名称

    private Date createDate; //创建日期

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon == null ? null : menuIcon.trim();
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName == null ? null : creatorName.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}