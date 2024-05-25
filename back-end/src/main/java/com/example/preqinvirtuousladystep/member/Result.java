package com.example.preqinvirtuousladystep.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code; // 业务状态码
    private String message; // 提示信息
    private T data; // 响应数据

    // 定义状态码枚举
    public enum Code {
        SUCCESS(0, "操作成功"),
        FAILURE(1, "操作失败");

        private final int code;
        private final String description;

        Code(int code, String description) {
            this.code = code;
            this.description = description;
        }
    }

    // 快速返回操作成功响应结果(带响应数据)
    public static <E> Result<E> success(E data) {
        return new Result<>(Code.SUCCESS.code, Code.SUCCESS.description, data);
    }

    // 快速返回操作成功响应结果
    public static Result success() {
        return new Result<>(Code.SUCCESS.code, Code.SUCCESS.description, null);
    }

    // 快速返回错误响应结果
    public static Result error(String message) {
        return new Result<>(Code.FAILURE.code, message, null);
    }

    // 根据状态码和消息快速返回错误响应结果
    public static Result error(Code code, String message) {
        return new Result<>(code.code, message, null);
    }
}
