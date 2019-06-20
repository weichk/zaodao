package com.acooly.zaodao.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/8/22.
 */
@Service("redisClientServiceImpl")
public class RedisClientServiceImpl implements RedisClientService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public void setRedis(String key, Object value) {
		redisTemplate.opsForValue().set(key, value);
	}

	@Override
	public Object getRedis(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	@Override
	public void delRedis(String key) {
		redisTemplate.delete(key);
	}

	@Override
	public void addRedis(String key) {
		Integer value = (Integer) redisTemplate.opsForValue().get(key);
		if (value != null) {
			value = value + 1;
		} else {
			value = 1;
		}
		redisTemplate.opsForValue().set(key, value);
	}

	@Override
	public void subRedis(String key, Integer num) {
		if (num == 0) {
			return;
		}
		Integer value = (Integer) redisTemplate.opsForValue().get(key);
		if (value != null) {
			if (value <= num) {
				redisTemplate.delete(key);
			} else {
				redisTemplate.opsForValue().set(key, value - num);
			}
		}
	}
}
