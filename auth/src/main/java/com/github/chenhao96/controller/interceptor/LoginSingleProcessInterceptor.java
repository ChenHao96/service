package com.github.chenhao96.controller.interceptor;

import com.github.chenhao96.model.LoginUserInfo;
import com.github.chenhao96.service.LoginSingleProcessService;
import com.github.chenhao96.utils.JsonUtils;
import com.github.chenhao96.utils.StringUtils;
import com.github.chenhao96.utils.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class LoginSingleProcessInterceptor extends AbstractInterceptor {

    public static final String ONLINE_USER_PREFIX_KEY = "online_user_";
    public static final String ACCOUNT_LOGIN_TMP_PATH = "account_login_tmp_path";
    public static final Set<String> USER_NAME_SET = new HashSet<>();

    @Resource
    private LoginSingleProcessService loginSingleProcessService;

    @Value("${account_login_tmp_path}")
    private String accountLoginTmpPath;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String userName = request.getParameter(USER_NAME);
        if (StringUtils.isNotEmpty(userName)) {
            HttpSession session = request.getSession();
            ServletContext context = session.getServletContext();
            String key = ONLINE_USER_PREFIX_KEY + userName;
            LoginUserInfo info = (LoginUserInfo) context.getAttribute(key);
            if (info == null) return true;
            if (info.getStatus() != null && info.getStatus() >= 0) return true;
        }

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

        HttpSession session = httpServletRequest.getSession();
        ServletContext context = session.getServletContext();
        String sessionId = session.getId();
        String userName = (String) session.getAttribute(USER_NAME);
        session.setAttribute(ACCOUNT_LOGIN_TMP_PATH, accountLoginTmpPath);
        if (StringUtils.isNotEmpty(userName)) {

            String key = ONLINE_USER_PREFIX_KEY + userName;
            LoginUserInfo info = (LoginUserInfo) context.getAttribute(key);
            if (info == null) {
                info = loadLoginUserInfo(userName);
            }

            if (StringUtils.isNotEmpty(info.getCurrentIp())) {
                info.setLastIp(info.getCurrentIp());
            }
            if (info.getCurrentLoginTime() != null) {
                info.setLastLoginTime(info.getCurrentLoginTime());
            }

            info.setStatus(1);
            info.setSessionId(sessionId);
            info.setCurrentIp(Utils.getIp(httpServletRequest));
            info.setCurrentLoginTime(new Date());
            context.setAttribute(key, info);
            USER_NAME_SET.add(key);
            loginSingleProcessService.insertLoginRecord(info);
        }
    }

    private LoginUserInfo loadLoginUserInfo(String userName) {

        LoginUserInfo result = null;
        File userLogin = new File(accountLoginTmpPath, userName + ".login");
        if (userLogin.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(userLogin);
                StringBuilder sb = new StringBuilder();
                int len;
                byte[] tmp = new byte[1024];
                while ((len = fis.read(tmp)) != -1) {
                    sb.append(new String(tmp, 0, len));
                }
                result = JsonUtils.jsonStr2Object(sb.toString(), LoginUserInfo.class);
            } catch (Exception ignored) {
                ignored.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException ignored) {
                    }
                }
            }
        }

        return result == null ? new LoginUserInfo(userName) : result;
    }
}
