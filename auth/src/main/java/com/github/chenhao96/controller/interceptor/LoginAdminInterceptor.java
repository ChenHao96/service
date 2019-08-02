package com.github.chenhao96.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
public class LoginAdminInterceptor extends AbstractInterceptor {

    public static final String ADMIN_SESSION_KEY = "admin_session_key";

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        Object admin = request.getSession().getAttribute(ADMIN_SESSION_KEY);
        if (admin == null) {
            responseInterceptorMsg(response, request, 20001, "登录超时,请重新登录!");
            return false;
        }

        return true;
    }
}
