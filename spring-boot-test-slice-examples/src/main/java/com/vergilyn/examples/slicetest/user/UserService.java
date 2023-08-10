package com.vergilyn.examples.slicetest.user;

import com.vergilyn.examples.slicetest.cache.UserCache;
import com.vergilyn.examples.slicetest.entity.UserEntity;
import com.vergilyn.examples.slicetest.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserCache userCache;
    @Autowired
    private UserMapper userMapper;

    public UserEntity getById(Integer id){
        UserEntity user = userCache.getById(id);
        if (user == null){
            user = userMapper.selectById(id);

            if (user != null){
                userCache.set(user);
            }
        }

        return user;
    }
}
