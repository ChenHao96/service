package com.github.chenhao96.controller.interceptor;

import com.github.chenhao96.model.LoginUserInfo;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginSingleInterceptor extends AbstractInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        HttpSession session = httpServletRequest.getSession();
        String sessionId = session.getId();

        ServletContext context = session.getServletContext();
        String userName = (String) session.getAttribute(USER_NAME);
        if (userName == null) return false;
        String key = LoginSingleProcessInterceptor.ONLINE_USER_PREFIX_KEY + userName;

        LoginUserInfo info = (LoginUserInfo) context.getAttribute(key);
        if (info == null) return false;
        if (!sessionId.equals(info.getSessionId())) {
            session.invalidate();
        }

        return true;
    }
}
