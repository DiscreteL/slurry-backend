package com.example.loginspr.service;

import com.example.loginspr.bean.UserBean;
import com.example.loginspr.common.Result;

public interface UserService {
    Result<?> selectUserName(UserBean user);
//    String addUser(UserBean user);

}
