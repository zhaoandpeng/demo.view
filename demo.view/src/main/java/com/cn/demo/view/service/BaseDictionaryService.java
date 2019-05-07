package com.cn.demo.view.service;

import java.util.concurrent.ConcurrentHashMap;

import com.cn.demo.view.model.BaseDictionary;
import com.cn.demo.view.utils.PageHelper;

public interface BaseDictionaryService extends BaseService<BaseDictionary, java.lang.String>{

	@SuppressWarnings("rawtypes")
	public PageHelper getListMapByPage(ConcurrentHashMap<String,Object> map, PageHelper page);
}
