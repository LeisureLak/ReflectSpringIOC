package com.lak.dao.impl;

import com.lak.annotation.Bean;
import com.lak.dao.IBookDao;
import com.lak.entity.Book;

import java.util.List;
@Bean
public class BookDaoImpl implements IBookDao {
    @Override
    public Book findBookById(int id) {
        System.out.println("这里是findBookById");
        return null;
    }

    @Override
    public List<Book> findAllBooks() {
        System.out.println("这里是findAllBooks");
        return null;
    }

    @Override
    public void saveBook(Book book) {
        System.out.println("这里是saveBook");

    }
}
