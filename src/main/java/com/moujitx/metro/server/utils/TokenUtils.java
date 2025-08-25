package com.moujitx.metro.server.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.RegisteredPayload;

import com.moujitx.metro.server.exception.AuthorizationException;
import com.moujitx.metro.server.exception.ServiceException;

public class TokenUtils {

    private static final Integer EXPIRE_TIME_MINUTE = 60 * 2;

    private static final String KEY = "MOUJITX";

    public static String generateToken(String uuid) {
        try {
            return JWT.create()
                    .setPayload("uuid", uuid)
                    .setPayload(RegisteredPayload.ISSUED_AT, DateTime.now())
                    .setPayload(RegisteredPayload.NOT_BEFORE, DateTime.now())
                    .setPayload(
                            RegisteredPayload.EXPIRES_AT,
                            DateTime.now().offsetNew(DateField.MINUTE, EXPIRE_TIME_MINUTE))
                    .setKey(KEY.getBytes())
                    .sign();
        } catch (Exception e) {
            throw new ServiceException("Generate token failed");
        }
    }

    public static String getUUID(String token) {
        try {
            return JWT.of(token).getPayload("uuid").toString();
        } catch (Exception e) {
            throw new AuthorizationException("Get uuid failed");
        }
    }

    public static Boolean verifyToken(String token) {
        try {
            return JWT.of(token).setKey(KEY.getBytes()).validate(0);
        } catch (Exception e) {
            return false;
        }
    }
}
