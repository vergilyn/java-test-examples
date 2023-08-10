package com.vergilyn.examples.slicetest.cache;

import com.vergilyn.examples.slicetest.entity.UserEntity;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class UserCache {
    @Resource
    private RedisTemplate<String, UserEntity> redisTemplate;

    public UserEntity getById(Integer id) {
        return redisTemplate.opsForValue().get(id);
    }

    public void set(UserEntity user) {
        if (user == null){
            return;
        }

        redisTemplate.opsForValue().set(key(user.getId()), user, 1, TimeUnit.HOURS);
    }

    private String key(Integer id){
        return "user:" + id;
    }
}
