package com.lak.dao;

import com.lak.entity.Book;

import java.util.List;

public interface IBookDao {
    /**
     * 根据id找到一个book
     * @param id
     * @return
     */
    Book findBookById(int id);

    /**
     * 获取所有的书籍
     * @return
     */
    List<Book> findAllBooks();

    /**
     * 保存书籍
     * @param book
     */
    void saveBook(Book book);
}
