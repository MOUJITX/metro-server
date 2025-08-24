package com.moujitx.metro.server.common;

import lombok.*;

/**
 * Function:消息统一返回接口
 * Author: MOUJITX
 * Date: 2023/9/17 21:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {
    public static final Integer CODE_SUCCESS_OK = 200;
    public static final Integer CODE_SUCCESS_CREATED = 201;
    public static final Integer CODE_ERROR_BAD_REQUEST = 400;
    public static final Integer CODE_ERROR_UNAUTHORIZED = 401;
    public static final Integer CODE_ERROR_FORBIDDEN = 403;
    public static final Integer CODE_ERROR_NOT_FOUND = 404;
    public static final Integer CODE_ERROR_METHOD_NOT_ALLOWED = 405;
    public static final Integer CODE_ERROR_REQUEST_TIMEOUT = 408;
    public static final Integer CODE_ERROR_CONFLICT = 409;
    public static final Integer CODE_ERROR_INTERNAL_SERVER_ERROR = 500;

    private Integer code;
    private String msg;
    private Object data;

    public static Result ok() {
        return new Result(CODE_SUCCESS_OK, "Request Success", null);
    }

    public static Result ok(Object data) {
        return new Result(CODE_SUCCESS_OK, "Request Success", data);
    }

    public static Result ok(String msg) {
        return new Result(CODE_SUCCESS_OK, msg, null);
    }

    public static Result ok(String title, Object data) {
        return new Result(CODE_SUCCESS_OK, title, data);
    }

    public static Result created() {
        return new Result(CODE_SUCCESS_CREATED, "Request Success", null);
    }

    public static Result created(Object data) {
        return new Result(CODE_SUCCESS_CREATED, "Request Success", data);
    }

    public static Result created(String msg) {
        return new Result(CODE_SUCCESS_CREATED, msg, null);
    }

    public static Result created(String title, Object data) {
        return new Result(CODE_SUCCESS_CREATED, title, data);
    }

    public static Result badRequest() {
        return new Result(CODE_ERROR_BAD_REQUEST, "Bad Request", null);
    }

    public static Result badRequest(String data) {
        return new Result(CODE_ERROR_BAD_REQUEST, "Bad Request", data);
    }

    public static Result unauthorized(String data) {
        return new Result(CODE_ERROR_UNAUTHORIZED, "Unauthorized", data);
    }

    public static Result notFound() {
        return new Result(CODE_ERROR_NOT_FOUND, "Not Found", null);
    }

    public static Result notFound(String data) {
        return new Result(CODE_ERROR_NOT_FOUND, "Not Found", data);
    }

    public static Result conflict() {
        return new Result(CODE_ERROR_CONFLICT, "Conflict", null);
    }

    public static Result conflict(String data) {
        return new Result(CODE_ERROR_CONFLICT, "Conflict", data);
    }

    public static Result internalServerError(String data) {
        return new Result(CODE_ERROR_INTERNAL_SERVER_ERROR, "Internal Server Error", data);
    }
}