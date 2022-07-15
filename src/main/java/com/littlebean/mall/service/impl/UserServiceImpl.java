package com.littlebean.mall.service.impl;

import com.littlebean.mall.exception.MallException;
import com.littlebean.mall.exception.MallExceptionEnum;
import com.littlebean.mall.model.dao.UserMapper;
import com.littlebean.mall.model.pojo.User;
import com.littlebean.mall.service.UserService;
import com.littlebean.mall.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User gerUser() {
        return userMapper.selectByPrimaryKey(4);
    }

    @Override
    public void register(String userName, String password) throws MallException {
        //查询用户名是否存在且不能重复
        User result = userMapper.selectByName(userName);
        if(result != null){
            throw new MallException(MallExceptionEnum.NAME_EXISTED);
        }

        //写到数据库
        User user=new User();
        user.setUsername(userName);
        try {
            user.setPassword(MD5Utils.getMD5Str(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        int count=userMapper.insertSelective(user);
        if(count == 0){
            throw new MallException(MallExceptionEnum.INSERT_FAILED);
        }
    }

    @Override
    public User login(String userName, String password) throws MallException {
        String md5Password = null;
        try {
            md5Password = MD5Utils.getMD5Str(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        User user = userMapper.selectLogin(userName, md5Password);
        if(user ==null){
            throw new MallException(MallExceptionEnum.WRONG_PASSWORD);
        }
        return user;
    }

    @Override
    public void updateInformation(User user) throws MallException {
        int count=userMapper.updateByPrimaryKeySelective(user);
        if(count>1){
            throw new MallException(MallExceptionEnum.UPDATE_FAILD);
        }
    }

    @Override
    public boolean checkAdminRole(User user){
        // 1是普通用户，2是管理员
        return user.getRole().equals(2);
    }
}
