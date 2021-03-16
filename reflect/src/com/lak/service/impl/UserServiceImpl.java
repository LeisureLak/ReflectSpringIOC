package com.lak.service.impl;

import com.lak.annotation.AutoWired;
import com.lak.annotation.Bean;
import com.lak.dao.IUserDao;
import com.lak.dao.impl.UserDaoImpl;
import com.lak.entity.User;
import com.lak.service.IUserService;

@Bean
public class UserServiceImpl implements IUserService {


    @AutoWired
    private IUserDao userDao;

    @Override
    public void login() {
        userDao.findUserByUserName();
        System.out.println("这是登陆业务的实现方法！");
    }

    @Override
    public void register() {
        userDao.saveUser(new User("1", "12", "123", 12));
        System.out.println("这是注册业务的实现方法！");
    }
}
