package com.cn.demo.view.redis;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.cn.demo.view.model.BaseUser;
import com.google.gson.Gson;
@Component
public class SpringGloabUserRedisUtils {

	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	
	/**
	 * 将用户持久化到Redis中
	 * @param key
	 * @param time
	 * @param baseUser
	 */
	public void add(final String key, long time, BaseUser baseUser) {
		
		Gson gson = new Gson();
		
		redisTemplate.opsForValue().setIfAbsent(key, gson.toJson(baseUser), time, TimeUnit.MINUTES);
	}
	
	/**
	 * 将用户从Redis中清除
	 * @param key
	 */
	public void delete(final String key) {
		
		redisTemplate.opsForValue().getOperations().delete(key);
	}
	
	/**
	 * 从Redis中获取用户实体
	 * @param key
	 */
	public void get(final String key) {
		
		redisTemplate.opsForValue().get(key);
	}
	
}
