package com;

import com.lak.dao.IUserDao;
import com.lak.entity.Book;
import com.lak.entity.User;
import com.lak.reflect.ApplicationContext;
import com.lak.service.IBookService;
import com.lak.service.IUserService;
import com.lak.service.impl.UserServiceImpl;

public class BootStrap {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ApplicationContext();
        /*applicationContext.initContext();
        System.out.println(applicationContext.getBean(IUserService.class));*/
        applicationContext.initContextByAnnotation();
        IBookService bookService = (IBookService)applicationContext.getBean(IBookService.class);
        bookService.borrow(new User("1", "sd", "123", 11), new Book());
    }
}
