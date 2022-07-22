package com.vergilyn.examples.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 *
 * @author vergilyn
 * @since 2022-06-10
 */
@Data
@ToString
@TableName("test_user")
public class UserEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private LocalDateTime createTime;
}
