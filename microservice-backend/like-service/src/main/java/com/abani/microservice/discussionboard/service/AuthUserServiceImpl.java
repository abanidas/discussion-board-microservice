package com.abani.microservice.discussionboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthUserServiceImpl implements AuthUserService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Map<Object, Object> findAuthUserByUsername(String username) {

        return redisTemplate.opsForHash().entries(username);
    }
}
