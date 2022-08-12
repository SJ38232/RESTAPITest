package com.internProject.SJ.api.jwt;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

public class RedisRepositoryConfig {
	private final RedisProperties redisProperties;
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
	    return new LettuceConnectionFactory(redisProperties.getHost(), redisProperties.getPort());
	}
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
	    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
	    redisTemplate.setConnectionFactory(redisConnectionFactory());
	    redisTemplate.setKeySerializer(new StringRedisSerializer());
	    redisTemplate.setValueSerializer(new StringRedisSerializer());
	    return redisTemplate;
	}
}
