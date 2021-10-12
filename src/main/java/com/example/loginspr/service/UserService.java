package com.example.loginspr.service;

import com.example.loginspr.bean.UserBean;
import com.example.loginspr.bean.UsersBo;
import com.example.loginspr.common.Result;

public interface UserService {
    Result<?> selectUserName(UserBean user);
    Result<?> addUser(UserBean user);
    Result<?> email(UsersBo user);
    public String randomCode(); //生成随机验证码
}
