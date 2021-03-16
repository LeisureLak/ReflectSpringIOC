package com.lak.dao.impl;

import com.lak.annotation.Bean;
import com.lak.dao.IUserDao;
import com.lak.entity.User;

import java.util.List;

@Bean
public class UserDaoImplOther implements IUserDao {
    @Override
    public User findUserById(int id) {
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        return null;
    }

    @Override
    public User findUserByUserName() {
        return null;
    }

    @Override
    public void saveUser(User user) {

    }
}
