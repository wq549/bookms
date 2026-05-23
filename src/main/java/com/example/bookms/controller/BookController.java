package com.example.bookms.controller;

import com.example.bookms.common.Result;
import com.example.bookms.entity.Book;
import com.example.bookms.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    // 查询所有
    @GetMapping("/findAll")
    public Result findAll() {
        List<Book> list = bookService.findAll();
        return Result.success(list);
    }

    @GetMapping("/search")
    public Result search(@RequestParam String keyword) {
        List<Book> list = bookService.searchBookByName(keyword);
        if (list.isEmpty()) {
            return Result.fail("没有找到相关图书");
        }
        return Result.success(list);
    }

    // 添加
    @PostMapping("/add")
    public Result add(@RequestBody @Validated Book book) {
        // 新增：给图书设置默认状态为“可借”
        book.setStatus("可借");
        bookService.add(book);
        return Result.success("添加成功");
    }

    // 删除
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        bookService.delete(id);
        return Result.success("删除成功");
    }

    // 根据ID查询
    @GetMapping("/findById/{id}")
    public Result findById(@PathVariable Integer id) {
        Book book = bookService.findById(id);
        return Result.success(book);
    }

    // 修改
    @PostMapping("/update")
    public Result update(@RequestBody Book book) {
        bookService.update(book);
        return Result.success("修改成功");
    }

    @PostMapping("/borrow/{id}")
    public Result borrowBook(@PathVariable Integer id){
        int count = bookService.borrowBook(id);
        if(count > 0){
            return Result.success("借阅成功");
        }
        return Result.fail("图书已借出，无法借阅");
    }

    @PostMapping("/returnBook/{id}")
    public Result returnBook(@PathVariable Integer id){
        int count = bookService.returnBook(id);
        if(count > 0){
            return Result.success("归还成功");
        }
        return Result.fail("图书未借出，无需归还");
    }
}