package com.vergilyn.examples.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vergilyn.examples.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

}
