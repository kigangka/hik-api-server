package com.lyc.hik.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 测试用户表
 *
 * @author kisang
 */
@Data
@TableName("sys_user")
public class SysUserEntity {

    @TableId(type = IdType.INPUT)
    private Long userId;

    private String username;

    private String password;

    private String email;

    private String createTime;

    private String updateTime;
}
