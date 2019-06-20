package com.acooly.zaodao.platform.service;

/**
 * Created by Administrator on 2017/8/22.
 */
public interface RedisClientService {

	/**
	 * 设置redis值
	 *
	 * @param key
	 * @param value
	 */
	void setRedis(String key, Object value);

	/**
	 * 获取redis值
	 *
	 * @param key
	 */
	Object getRedis(String key);

	/**
	 * 删除redis值
	 *
	 * @param key
	 */
	void delRedis(String key);

	/**
	 * 增加消息计数
	 *
	 * @param key
	 */
	void addRedis(String key);

	/**
	 * 减少消息计数
	 *
	 * @param key
	 */
	void subRedis(String key,Integer num);
}
