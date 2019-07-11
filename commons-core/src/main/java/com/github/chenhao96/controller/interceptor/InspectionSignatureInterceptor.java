package com.github.chenhao96.controller.interceptor;


import com.github.chenhao96.utils.StringUtils;
import com.github.chenhao96.utils.Utils;
import com.github.chenhao96.utils.request.RequestOverloadService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证签名的拦截器
 */
public class InspectionSignatureInterceptor extends AbstractInterceptor {

    public static final String USERNAME = "userName";

    @Resource
    private RequestOverloadService requestOverloadService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String deviceId = Utils.getIPAndPort(request);
        String url = request.getRequestURI();
        long lastTime = System.currentTimeMillis();
        String userName = (String) request.getSession().getAttribute(USERNAME);
        if (StringUtils.isEmpty(userName)) {
            userName = "";
        }

        if (requestOverloadService.checkRequestOverLoad(userName, deviceId, url, lastTime)) {

            responseInterceptorMsg(response, request, -3, "请求过于频繁，请稍候再试！");
            return false;
        }

        return true;
    }
}
