package com.vergilyn.examples.slicetest.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("test_user")
public class UserEntity implements Serializable {

    @TableId
    private Integer id;

    private String username;

    private String nickname;

    private String email;

    private Date createTime;
}
