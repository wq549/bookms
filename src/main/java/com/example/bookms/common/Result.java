package com.example.bookms.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private int code; // 200成功 500失败
    private String msg;
    private Object data;

    public static Result success(Object data) {
        return new Result(200, "操作成功", data);
    }

    public static Result fail(String msg) {
        return new Result(500, msg, null);
    }
}