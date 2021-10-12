package com.example.loginspr.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("user")
@Data
public class UserBean {
    @TableId(type = IdType.AUTO)
    private int id;
    private String username;
    private String password;
    private String email;

    @TableField(exist = false)
    private String token;
}