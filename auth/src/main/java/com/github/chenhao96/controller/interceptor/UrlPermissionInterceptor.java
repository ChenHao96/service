package com.github.chenhao96.controller.interceptor;

import com.github.chenhao96.controller.UsersController;
import com.github.chenhao96.model.enumeration.HttpStatusEnum;
import com.github.chenhao96.model.vo.AdminVo;
import com.github.chenhao96.utils.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求权限拦截器
 */
public class UrlPermissionInterceptor extends AbstractInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AdminVo admin = (AdminVo) request.getSession().getAttribute(UsersController.ADMIN_SESSION_KEY);
        if (admin != null && admin.getUrls().size() > 0) {
            String requestUrl = (String) request.getAttribute(RequestInterceptor.SERVLET_PATH_PARAMETER_NAME);
            if (CollectionUtils.isNotEmpty(admin.getUrls())) {
                if (admin.getUrls().contains(requestUrl)) {
                    return true;
                }
            }
        }
        responseInterceptorMsg(response, request, HttpStatusEnum.URL_PERMISSION_FAULT.getCode(), HttpStatusEnum.URL_PERMISSION_FAULT.getDescription());
        return false;
    }
}
