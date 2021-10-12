package com.example.loginspr.controller;

import com.example.loginspr.bean.UserBean;
import com.example.loginspr.common.Result;
import com.example.loginspr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    //将Service注入Web层
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    @CrossOrigin(value = "http://localhost:8080", maxAge = 1800, allowedHeaders ="*")
    public Result<?> login(@RequestBody UserBean user) {
        return userService.selectUserName(user);
    }

   /* @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@RequestBody UserBean user) {
        return userService.addUser(user);
    }
*/
}
