package com.cn.demo.view.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.demo.view.dao.BaseDao;
import com.cn.demo.view.model.BaseDictionary;
import com.cn.demo.view.service.BaseDictionaryService;
import com.cn.demo.view.utils.PageHelper;
@Service
public class BaseDictionaryServiceImpl  extends BaseServiceImpl<BaseDictionary,java.lang.String> implements BaseDictionaryService{

	
	@Resource(name = "baseDaoImpl")
	@SuppressWarnings("rawtypes")
	private BaseDao baseDao;
	
	/**
	  *  获取字典数据列表
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageHelper getListMapByPage(ConcurrentHashMap<String,Object> map, PageHelper page){
		
		StringBuffer buffer = new StringBuffer(" select t.*, IFNULL (a.item_code, 0) as PARENT_ITEM_CODE from base_dictionary t left join base_dictionary a on t.pid = a.id where 1 = 1");
		
		if(null!= map&&!map.isEmpty()) {
			
			for (Map.Entry<String, Object>  entry  : map.entrySet()) {
				
				buffer.append(" and "+entry.getKey()+"='"+entry.getValue().toString()+"'");
			}
		}
		
		return baseDao.getListMapPage(buffer, page);
	}

	
}
