package com.example.loginspr.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.loginspr.bean.UserBean;
import com.example.loginspr.mapper.UserMapper;
import com.example.loginspr.service.UserService;
import com.example.loginspr.utils.TokenUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.loginspr.common.Result;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    //将DAO注入Service层
    @Resource
    private UserMapper userMapper;

    @Override
    public Result<?> selectUserName(@RequestBody UserBean user) {
        QueryWrapper<UserBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        queryWrapper.eq("password", user.getPassword());
        UserBean res = userMapper.selectOne(queryWrapper);
        if (res == null) {
            return Result.error("-1", "用户名或密码错误");
        }
        // 生成token
        String token = TokenUtils.genToken(res);
        res.setToken(token);
        return Result.success(res);
    }

    @Override
    public Result<?> addUser(@RequestBody UserBean user) {
        UserBean res = userMapper.selectOne(Wrappers.<UserBean>lambdaQuery().eq(UserBean::getUsername, user.getUsername()));
        if (res != null) {
            return Result.error("-1", "用户名重复");
        }
        userMapper.insert(user);
        return Result.success();
    }

}
