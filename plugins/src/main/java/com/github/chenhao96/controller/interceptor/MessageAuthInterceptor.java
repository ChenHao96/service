package com.github.chenhao96.controller.interceptor;

import com.github.chenhao96.service.MessageAuthService;
import com.github.chenhao96.utils.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MessageAuthInterceptor extends AbstractInterceptor {

    @Resource
    private MessageAuthService messageAuthService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUrl = (String) request.getAttribute(RequestInterceptor.SERVLET_PATH_PARAMETER_NAME);
        if (StringUtils.isNotEmpty(requestUrl)) {
            if (!messageAuthService.contains(requestUrl)) return true;
            String verifyCode = request.getParameter(MessageAuthService.VERIFY_CODE_KEY);
            HttpSession session = request.getSession();
            if (StringUtils.isNotEmpty(verifyCode)) {
                String cacheCode = (String) session.getAttribute(MessageAuthService.VERIFY_CODE_KEY);
                if (verifyCode.equals(cacheCode)) return true;
                responseInterceptorMsg(response, request, -1, "验证失败,请检查验证码!");
            } else {
                if (messageAuthService.sendVerifyCodeMessage(session)) {
                    responseInterceptorMsg(response, request, -403, "验证码发送中...");
                } else {
                    responseInterceptorMsg(response, request, -1, "验证码发送失败");
                }
            }
        }
        return false;
    }
}
