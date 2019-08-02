package com.github.chenhao96.controller.interceptor;

import com.github.chenhao96.controller.AbstractController;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractInterceptor implements HandlerInterceptor {

    public static final String USER_NAME = "userName";

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    protected static void responseInterceptorMsg(HttpServletResponse response, HttpServletRequest request, int code, String message) throws IOException {

        Map<String, Object> result = new HashMap<>(4);
        result.put("result", false);
        result.put("data", new int[0]);
        result.put("error_code", code);
        result.put("message", message);

        response.setContentType(AbstractController.CONTENT_TYPE_HTTP_HEADER);
        response.getWriter().println(AbstractController.fmtJsonPString(request, result));
        response.getWriter().flush();
    }
}
