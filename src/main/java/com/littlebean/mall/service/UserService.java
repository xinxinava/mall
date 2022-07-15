package com.littlebean.mall.service;

import com.littlebean.mall.exception.MallException;
import com.littlebean.mall.model.pojo.User;

public interface UserService {
    public User gerUser();

    public void register(String userName, String password) throws MallException;

    User login(String userName, String password) throws MallException;

    void updateInformation(User user) throws MallException;

    boolean checkAdminRole(User user);
}
