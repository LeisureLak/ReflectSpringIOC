package com.lak.service;

import com.lak.entity.Book;
import com.lak.entity.User;

public interface IBookService {

    void borrow(User user, Book books);
}
