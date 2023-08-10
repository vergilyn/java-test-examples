package com.vergilyn.examples.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vergilyn.examples.entity.UserEntity;
import com.vergilyn.examples.mapper.UserMapper;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author vergilyn
 * @since 2022-06-10
 */
public class UserService extends ServiceImpl<UserMapper, UserEntity> {
    public static final String BEAN_NAME = "userService";

    @Getter
    private final String beanName;

    public UserService(String beanName) {
        this.beanName = beanName;
    }

    public UserEntity save(String username){
        UserEntity entity = new UserEntity();
        entity.setId(null);
        entity.setUsername(username);
        entity.setCreateTime(LocalDateTime.now());

        save(entity);

        return entity;
    }

    public int delete(String username){
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserEntity::getUsername, username);

        return getBaseMapper().delete(queryWrapper);
    }

    public List<UserEntity> get(String username){
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserEntity::getUsername, username);

        return getBaseMapper().selectList(queryWrapper);
    }
}
