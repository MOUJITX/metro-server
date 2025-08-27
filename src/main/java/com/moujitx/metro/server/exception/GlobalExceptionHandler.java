package com.moujitx.metro.server.exception;

import com.moujitx.metro.server.common.Result;
import com.qiniu.common.QiniuException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Function: 自定义异常
 * Author: MOUJITX
 * Date: 2023/9/18 21:24
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result serviceException(ServiceException e) {
        return Result.internalServerError(e.getMessage());
    }

    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public Result authorizationException(AuthorizationException e) {
        return Result.unauthorized(e.getMessage());
    }

    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public Result sqlException(SQLException e) {
        // System.out.println(e.getSQLState());
        // System.out.println(e.getErrorCode());
        // System.out.println(e);
        switch (e.getErrorCode()) {
            case 1062:
                return Result.conflict(e.getMessage());
            default:
                return Result.internalServerError(e.getMessage());
        }
    }

    @ExceptionHandler(IOException.class)
    @ResponseBody
    public Result ioException(IOException e) {
        return Result.internalServerError(e.getMessage());
    }

    @ExceptionHandler(QiniuException.class)
    @ResponseBody
    public Result qiniuException(QiniuException e) {
        return Result.internalServerError(e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public Result exception(NoHandlerFoundException e) {
        return Result.internalServerError(e.getMessage());
    }
}
