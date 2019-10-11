package com.github.chenhao96.controller.interceptor;

import com.github.chenhao96.controller.BaseController;
import com.github.chenhao96.service.MessageAuthService;
import com.github.chenhao96.utils.DateUtil;
import com.github.chenhao96.utils.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MessageAuthInterceptor extends AbstractInterceptor {

    private static final Map<String, AtomicInteger> record = new LinkedHashMap<>();
    private static final Map<String, AtomicInteger> cache_pass_ip_count = new LinkedHashMap<>();

    @Resource
    private MessageAuthService messageAuthService;

    private AtomicInteger getPassIp(String ip) {
        AtomicInteger integer = cache_pass_ip_count.get(ip);
        if (integer == null) {
            integer = new AtomicInteger(0);
            cache_pass_ip_count.put(ip, integer);
        }
        return integer;
    }

    private AtomicInteger getAndRecordIp(final String ip) {
        AtomicInteger integer = record.get(ip);
        if (integer == null) {
            integer = new AtomicInteger(0);
            record.put(ip, integer);
            Timer timer = new Timer(true);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    record.remove(ip);
                }
            }, DateUtil.timeNextSecondNewDate(new Date()));
        }
        return integer;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = (String) request.getAttribute(BaseController.CLIENT_IP_KEY);
        if (getPassIp(ip).decrementAndGet() > 0) return true;
        String requestUrl = (String) request.getAttribute(RequestInterceptor.SERVLET_PATH_PARAMETER_NAME);
        if (getAndRecordIp(ip).incrementAndGet() >= messageAuthService.enableFailCount()) return false;
        if (StringUtils.isNotEmpty(requestUrl)) {
            if (!messageAuthService.contains(requestUrl)) return true;
            String verifyCode = request.getParameter(MessageAuthService.VERIFY_CODE_KEY);
            if (StringUtils.isNotEmpty(verifyCode)) {
                HttpSession session = request.getSession();
                String cacheCode = (String) session.getAttribute(MessageAuthService.VERIFY_CODE_KEY);
                if (verifyCode.equals(cacheCode)) {
                    session.setAttribute(MessageAuthService.VERIFY_CODE_KEY, null);
                    getAndRecordIp(ip).set(0);
                    getPassIp(ip).set(3);
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
        return false;
    }
}
