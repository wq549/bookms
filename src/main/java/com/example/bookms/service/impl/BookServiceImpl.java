package com.example.bookms.service.impl;

import com.example.bookms.entity.Book;
import com.example.bookms.mapper.BookMapper;
import com.example.bookms.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> findAll() {
        return bookMapper.findAll();
    }

    @Override
    public List<Book> searchBookByName(String keyword) {
        return bookMapper.searchBookByName(keyword);
    }
    @Override
    public void add(Book book) {
        bookMapper.add(book);
    }

    @Override
    public void delete(Integer id) {
        bookMapper.delete(id);
    }

    @Override
    public Book findById(Integer id) {
        return bookMapper.findById(id);
    }

    @Override
    public void update(Book book) {
        bookMapper.update(book);
    }

    @Override
    public int borrowBook(Integer id) {
        return bookMapper.borrowBook(id);
    }
    @Override
    public int returnBook(Integer id) {
        return bookMapper.returnBook(id);
    }
}