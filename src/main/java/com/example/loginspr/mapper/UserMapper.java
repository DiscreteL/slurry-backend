package com.example.loginspr.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.loginspr.bean.UserBean;
import com.example.loginspr.bean.UsersBo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<UserBean> {
    UserBean getInfo(String name,String password);

}
