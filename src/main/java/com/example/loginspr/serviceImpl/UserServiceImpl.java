package com.example.loginspr.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.loginspr.bean.UserBean;
import com.example.loginspr.mapper.UserMapper;
import com.example.loginspr.service.UserService;
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

    @PostMapping("/login")
    public Result<?> selectUserName(@RequestBody UserBean user) {
        QueryWrapper<UserBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        queryWrapper.eq("password", user.getPassword());
        UserBean res = userMapper.selectOne(queryWrapper);
        if (res == null) {
            return Result.error("-1", "用户名或密码错误");
        }
        // 生成token
//        String token = TokenUtils.genToken(res);
//        res.setToken(token);
        return Result.success(res);
    }
//    @Override
//    public String selectUserName(UserBean user) {
//        String userName = userMapper.selectUserName(user.getName());
//        String userPassword = user.getPassword();
//        System.out.println(userName);
//        System.out.println(userPassword);
//
//        String result = "-1";
//
//        // 用户不存在
//        if (userMapper.selectUserName(userName) == null) {
//            result = "-1";
//            return result;
//            //  用户存在，但密码输入错误
//        }else if(!userMapper.selectUserPassword(userName).equals(userPassword) ){
//            result = "0";
//            return result;
//            //  登录成功
//        }else if(userMapper.selectUserPassword(userName).equals(userPassword)) {
//            result = "200";
//            return result;
//        }
//        return result;
//    }

   /* @Override
    public String addUser(UserBean user) {
        String userName = user.getName();
        // 用户存在
        if(userMapper.selectUserName(userName) != null)
            return "-1";
        String userPassword = user.getPassword();
        System.out.println(userName + "***" + userPassword);
        userMapper.addUser(userName, userPassword);
        return "200";
    }
*/
}
