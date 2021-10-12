package com.example.loginspr.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.loginspr.bean.UserBean;
import com.example.loginspr.bean.UsersBo;
import com.example.loginspr.mapper.UserMapper;
import com.example.loginspr.service.UserService;
import com.example.loginspr.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.loginspr.common.Result;

import javax.annotation.Resource;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    //将DAO注入Service层
    @Resource
    private UserMapper userMapper;

    @Autowired
    private JavaMailSenderImpl mailSender;

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

    @Override
    public Result<?> email(@RequestBody UsersBo user) {
        Result result = new Result();
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        UserBean res = userMapper.selectOne(Wrappers.<UserBean>lambdaQuery().eq(UserBean::getEmail, user.getEmail()));
        if (res != null) {
            return Result.error("-1", "该邮箱已被注册");
        }
        // 生成随机数
        String code = randomCode();

        simpleMailMessage.setSubject("泥水盾构验证码邮件"); // 主题
        simpleMailMessage.setText("您收到的验证码是：" + code); // 内容
        simpleMailMessage.setFrom("1752962271@qq.com");
        simpleMailMessage.setTo(user.getEmail()); // 收件人
        try {
            mailSender.send(simpleMailMessage);
        } // 发送

        catch (Exception e) {
            e.printStackTrace();
            return Result.error("-1", "验证码发送失败");
        }
        result.setEcode(code);
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    /**
     * 随机生成6位数的验证码
     *
     * @return String code
     */
    @Override
    public String randomCode() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }
}
