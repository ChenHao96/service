package com.github.chenhao96.controller.interceptor;

import com.github.chenhao96.service.MessageAuthService;
import com.github.chenhao96.utils.StringUtils;
import com.github.chenhao96.utils.Utils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MessageAuthInterceptor extends AbstractInterceptor {

    private static final Map<String, AtomicInteger> record = new HashMap<>(20);

    @Resource
    private MessageAuthService messageAuthService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = Utils.getIp(request);
        String requestUrl = (String) request.getAttribute(RequestInterceptor.SERVLET_PATH_PARAMETER_NAME);
        AtomicInteger failCount = record.get(ip);
        if (failCount != null && failCount.get() >= messageAuthService.enableFailCount()) return false;
        if (failCount == null) failCount = new AtomicInteger();
        if (StringUtils.isNotEmpty(requestUrl)) {
            if (!messageAuthService.contains(requestUrl)) return true;
            String verifyCode = request.getParameter(MessageAuthService.VERIFY_CODE_KEY);
            if (StringUtils.isNotEmpty(verifyCode)) {
                HttpSession session = request.getSession();
                String cacheCode = (String) session.getAttribute(MessageAuthService.VERIFY_CODE_KEY);
                if (verifyCode.equals(cacheCode)) {
                    session.setAttribute(MessageAuthService.VERIFY_CODE_KEY, null);
                    failCount.set(0);
                    record.put(ip, failCount);
                    return true;
                }
                responseInterceptorMsg(response, request, -1, "验证失败,请检查验证码!");
            } else {
                if (messageAuthService.sendVerifyCodeMessage(request)) {
                    responseInterceptorMsg(response, request, -403, "验证码发送中...");
                } else {
                    responseInterceptorMsg(response, request, -1, "验证码发送失败");
                }
            }
        }
        failCount.incrementAndGet();
        record.put(ip, failCount);
        return false;
    }
}
