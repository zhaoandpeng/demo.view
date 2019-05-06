package com.cn.demo.view.model;

import java.io.Serializable;

import com.cn.demo.view.annotation.TableInfoAnnotation;

/**
 * 
 * 
 * @author mr.zhao
 * 
 * @date 2019-05-06
 */
@TableInfoAnnotation(tableName = "base_dictionary", primaryKey = "ID")
public class BaseDictionary implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ID;

    private String PID;

    private String ITEM_CODE;

    private String ITEM_NAME;

    private String ITEM_VALUE;

    private String STATUS;
    
    private String REMARK;

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

    public String getITEM_CODE() {
        return ITEM_CODE;
    }

    public void setITEM_CODE(String ITEM_CODE) {
        this.ITEM_CODE = ITEM_CODE == null ? null : ITEM_CODE.trim();
    }

    public String getITEM_NAME() {
        return ITEM_NAME;
    }

    public void setITEM_NAME(String ITEM_NAME) {
        this.ITEM_NAME = ITEM_NAME == null ? null : ITEM_NAME.trim();
    }

    public String getITEM_VALUE() {
        return ITEM_VALUE;
    }

    public void setITEM_VALUE(String ITEM_VALUE) {
        this.ITEM_VALUE = ITEM_VALUE == null ? null : ITEM_VALUE.trim();
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS == null ? null : STATUS.trim();
    }

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String REMARK) {
		this.REMARK = REMARK == null ? null : REMARK.trim();
	}
    
    
}