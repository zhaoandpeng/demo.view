package com.cn.demo.view.dao;

import com.cn.demo.view.model.BaseUser;

public interface BaseUserDao extends BaseDao<BaseUser, java.lang.String>{

	BaseUser getBaseUserByParam(String username);

}
