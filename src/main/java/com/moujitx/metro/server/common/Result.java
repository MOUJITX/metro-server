package com.moujitx.metro.server.common;

import lombok.*;

/**
 * Function:消息统一返回接口
 * Author: MOUJITX
 * Date: 2023/9/17 21:59
 */
@Data
@AllArgsConstructor
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

    public static final String MSG_REQUEST_SUCCESS = "Request Success";
    public static final String MSG_BAD_REQUEST = "Bad Request";
    public static final String MSG_UNAUTHORIZED = "Unauthorized";
    public static final String MSG_NOT_FOUND = "Not Found";
    public static final String MSG_CONFLICT = "Conflict";
    public static final String MSG_INTERNAL_SERVER_ERROR = "Internal Server Error";

    private Integer code;
    private String msg;
    private Object data;

    public static Result ok() {
        return new Result(CODE_SUCCESS_OK, MSG_REQUEST_SUCCESS, null);
    }

    public static Result ok(Object data) {
        return new Result(CODE_SUCCESS_OK, MSG_REQUEST_SUCCESS, data);
    }

    public static Result ok(String msg) {
        return new Result(CODE_SUCCESS_OK, msg, null);
    }

    public static Result ok(String title, Object data) {
        return new Result(CODE_SUCCESS_OK, title, data);
    }

    public static Result created() {
        return new Result(CODE_SUCCESS_CREATED, MSG_REQUEST_SUCCESS, null);
    }

    public static Result created(Object data) {
        return new Result(CODE_SUCCESS_CREATED, MSG_REQUEST_SUCCESS, data);
    }

    public static Result created(String msg) {
        return new Result(CODE_SUCCESS_CREATED, msg, null);
    }

    public static Result created(String title, Object data) {
        return new Result(CODE_SUCCESS_CREATED, title, data);
    }

    public static Result badRequest() {
        return new Result(CODE_ERROR_BAD_REQUEST, MSG_BAD_REQUEST, null);
    }

    public static Result badRequest(String data) {
        return new Result(CODE_ERROR_BAD_REQUEST, MSG_BAD_REQUEST, data);
    }

    public static Result unauthorized(String data) {
        return new Result(CODE_ERROR_UNAUTHORIZED, MSG_UNAUTHORIZED, data);
    }

    public static Result notFound() {
        return new Result(CODE_ERROR_NOT_FOUND, MSG_NOT_FOUND, null);
    }

    public static Result notFound(String data) {
        return new Result(CODE_ERROR_NOT_FOUND, MSG_NOT_FOUND, data);
    }

    public static Result conflict() {
        return new Result(CODE_ERROR_CONFLICT, MSG_CONFLICT, null);
    }

    public static Result conflict(String data) {
        return new Result(CODE_ERROR_CONFLICT, MSG_CONFLICT, data);
    }

    public static Result internalServerError(String data) {
        return new Result(CODE_ERROR_INTERNAL_SERVER_ERROR, MSG_INTERNAL_SERVER_ERROR, data);
    }
}