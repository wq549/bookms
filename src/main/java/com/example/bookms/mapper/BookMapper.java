package com.example.bookms.mapper;

import com.example.bookms.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface BookMapper {
    // 查询所有图书
    List<Book> findAll();

    List<Book> searchBookByName(String keyword);

    // 添加图书
    void add(Book book);

    // 删除图书
    void delete(Integer id);

    // 根据ID查询
    Book findById(Integer id);

    // 修改图书
    void update(Book book);

    int borrowBook(Integer id);
    int returnBook(Integer id);
}