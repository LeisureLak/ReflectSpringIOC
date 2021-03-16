package com.lak.dao.impl;

import com.lak.annotation.Bean;
import com.lak.dao.IUserDao;
import com.lak.entity.User;

import java.util.List;
@Bean
public class UserDaoImpl implements IUserDao {

    @Override
    public User findUserById(int id) {
        System.out.println("这里是findUserById");
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        System.out.println("这里是findAllUsers");
        return null;
    }

    @Override
    public User findUserByUserName() {
        System.out.println("这里是findUserByUserName");
        return null;
    }

    @Override
    public void saveUser(User user) {
        System.out.println("这里是saveUser");

    }
}
