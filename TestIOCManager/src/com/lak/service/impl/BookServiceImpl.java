package com.lak.service.impl;

import com.lak.annotation.AutoWired;
import com.lak.annotation.Bean;
import com.lak.dao.IBookDao;
import com.lak.dao.IUserDao;
import com.lak.dao.impl.BookDaoImpl;
import com.lak.dao.impl.UserDaoImpl;
import com.lak.entity.Book;
import com.lak.entity.User;
import com.lak.service.IBookService;

import java.util.List;

@Bean
public class BookServiceImpl implements IBookService {

    @AutoWired
    private IUserDao userDao;
    @AutoWired
    private IBookDao bookDao;

    @Override
    public void borrow(User user, Book book) {
        List<User> allUsers = userDao.findAllUsers();
        System.out.println(user.getUsername() + "借了" + book.getName());
    }
}
