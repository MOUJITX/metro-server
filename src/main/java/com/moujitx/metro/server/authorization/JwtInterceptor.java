package com.moujitx.metro.server.authorization;

import cn.hutool.core.util.StrUtil;
import com.moujitx.metro.server.exception.AuthorizationException;
import com.moujitx.metro.server.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Function:token验证
 * Author: MOUJITX
 * Date: 2023/9/18 8:52
 */

@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    @SuppressWarnings("null")
    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        // 跳过拦截器
        if (handler instanceof HandlerMethod) {
            AuthAccess annotation = ((HandlerMethod) handler).getMethodAnnotation(AuthAccess.class);
            if (annotation != null) {
                return true;
            }
        }

        String token = request.getHeader("authorization");
        if (StrUtil.isBlank(token)) {
            token = request.getParameter("token");
        }

        if (StrUtil.isBlank(token)) {
            throw new AuthorizationException("TOKEN为空");
        }

        if (TokenUtils.verifyToken(token)) {
            return true;
        } else {
            throw new AuthorizationException();
        }
    }
}
