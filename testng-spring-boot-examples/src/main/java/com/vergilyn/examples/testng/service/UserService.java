package com.vergilyn.examples.testng.service;

import java.util.List;

import com.vergilyn.examples.testng.entity.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author vergilyn
 * @since 2021-07-30
 */
@Service
public class UserService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int save(String username){
        return jdbcTemplate.update("insert into test_user(username) values(?) ", username);
    }

    public int delete(String username){
        return jdbcTemplate.update("delete from test_user where username = ?", username);
    }

    public List<UserEntity> get(String username){
        return jdbcTemplate.query("select * from test_user where username = ?", new Object[]{username}, BeanPropertyRowMapper.newInstance(UserEntity.class));
    }
}
