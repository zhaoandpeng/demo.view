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
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("SELECT T.ID, T.PID, T.ITEM_CODE, T.ITEM_NAME, T.ITEM_VALUE, T.REMARK, (CASE WHEN T.`STATUS` = '1' THEN '启用'  WHEN T.`STATUS` = '0' THEN '禁用'  END) AS STATUS, IFNULL (A.ITEM_CODE, 0) AS PARENT_ITEM_CODE FROM BASE_DICTIONARY T LEFT JOIN BASE_DICTIONARY A ON T.PID = A.ID WHERE 1 = 1 ");
		
		if(null!= map&&!map.isEmpty()) {
			
			for (Map.Entry<String, Object>  entry  : map.entrySet()) {
				
				buffer.append(" and "+entry.getKey()+"='"+entry.getValue().toString()+"'");
			}
		}
		
		return baseDao.getListMapPage(buffer, page);
	}

	
}
