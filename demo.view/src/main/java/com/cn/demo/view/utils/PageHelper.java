package com.cn.demo.view.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class PageHelper<T> implements Serializable{

	public static final String ASC = "asc";
	
	public static final String DESC = "desc";
	
	protected int pageNo = 1; //页码
	
	protected int pageSize = 10; //每页条数
	
	protected int totalPage = 0; //每页条数
	
	protected String orderBy = null; //排序字段
	
	protected List<T> result = new ArrayList<T>();
	
	protected long totalCount = -1;
	
	public int getPageNo() {
		
		return pageNo;
	}
	
	public void setPageNo(final int pageNo) {
		
		this.pageNo = pageNo;
		
		if (pageNo < 1) { this.pageNo = 1; }
	}

	public int getPageSize() {
		
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		
		this.pageSize = pageSize;
		
		if (pageSize < 1) { this.pageSize = 1; }
	}

	public String getOrderBy() {
		
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		
		this.orderBy = orderBy;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
}
