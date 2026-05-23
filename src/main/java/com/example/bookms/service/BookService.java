package com.example.bookms.service;

import com.example.bookms.entity.Book;
import java.util.List;

public interface BookService {
    List<Book> findAll();
    void add(Book book);
    void delete(Integer id);
    Book findById(Integer id);
    void update(Book book);
    int borrowBook(Integer id);
    int returnBook(Integer id);

    List<Book> searchBookByName(String keyword);
}