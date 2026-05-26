package com.example.bookms.controller;

import com.example.bookms.common.Result;
import com.example.bookms.entity.Book;
import com.example.bookms.service.BookService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    // 查询所有 —— 所有人可用
    @GetMapping("/findAll")
    public Result findAll() {
        List<Book> list = bookService.findAll();
        return Result.success(list);
    }

    // 搜索 —— 所有人可用
    @GetMapping("/search")
    public Result search(@RequestParam String keyword) {
        List<Book> list = bookService.searchBookByName(keyword);
        if (list.isEmpty()) {
            return Result.fail("没有找到相关图书");
        }
        return Result.success(list);
    }

    // 根据ID查询 —— 所有人可用
    @GetMapping("/findById/{id}")
    public Result findById(@PathVariable Integer id) {
        Book book = bookService.findById(id);
        return Result.success(book);
    }

    // ==============================
    // 以下 仅管理员可用
    // ==============================

    // 添加
    @PostMapping("/add")
    public Result add(@RequestBody @Validated Book book, HttpSession session) {
        if (!isAdmin(session)) {
            return Result.fail("无权限，只有管理员可添加");
        }
        book.setStatus("可借");
        bookService.add(book);
        return Result.success(null);
    }

    // 删除
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id, HttpSession session) {
        if (!isAdmin(session)) {
            return Result.fail("无权限，只有管理员可删除");
        }
        bookService.delete(id);
        return Result.success(null);
    }

    // 修改
    @PostMapping("/update")
    public Result update(@RequestBody Book book, HttpSession session) {
        if (!isAdmin(session)) {
            return Result.fail("无权限，只有管理员可修改");
        }
        bookService.update(book);
        return Result.success(null);
    }

    // ==============================
    // 借阅、归还 —— 所有用户都能用
    // ==============================

    @PostMapping("/borrow/{id}")
    public Result borrowBook(@PathVariable Integer id) {
        int count = bookService.borrowBook(id);
        if (count > 0) {
            return Result.success("借阅成功");
        }
        return Result.fail("图书已借出，无法借阅");
    }

    @PostMapping("/returnBook/{id}")
    public Result returnBook(@PathVariable Integer id) {
        int count = bookService.returnBook(id);
        if (count > 0) {
            return Result.success("归还成功");
        }
        return Result.fail("图书未借出，无需归还");
    }

    // ==============================
    // 判断是否管理员（工具方法）
    // ==============================
    private boolean isAdmin(HttpSession session) {
        String role = (String) session.getAttribute("role");
        return "admin".equals(role);
    }
}