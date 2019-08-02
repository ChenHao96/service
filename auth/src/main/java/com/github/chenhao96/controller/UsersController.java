package com.github.chenhao96.controller;

import com.github.chenhao96.controller.interceptor.AbstractInterceptor;
import com.github.chenhao96.controller.interceptor.HitControllerInterceptor;
import com.github.chenhao96.encrypt.MD5Utils;
import com.github.chenhao96.model.bo.AdminBo;
import com.github.chenhao96.model.enumeration.HttpStatusEnum;
import com.github.chenhao96.model.vo.AdminVo;
import com.github.chenhao96.service.AdminService;
import com.github.chenhao96.service.ServiceResult;
import com.github.chenhao96.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UsersController extends BaseController {

    public static final String ADMIN_SESSION_KEY = "admin_session_key";
    private static final String ADMIN_SESSION_BUF_KEY = "admin_session_buf_key";
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

    @Resource
    private AdminService adminService;

    @RequestMapping(value = "/login")
    public void login(AdminBo bo) {

        ServiceResult<AdminVo> serviceResult = new ServiceResult<>();
        serviceResult.setResult(true);
        serviceResult.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
        serviceResult.setMessage(HttpStatusEnum.SUCCESS.getDescription());

        try {
            serviceResult = adminService.checkAdminLogin(bo);
            addAdminInSession(serviceResult);
        } catch (Exception e) {
            LOGGER.warn("login", e);
            serviceResult.setResult(false);
            serviceResult.setResponseCode(HttpStatusEnum.SYSTEM_ERROR.getCode());
            serviceResult.setMessage(HttpStatusEnum.SYSTEM_ERROR.getDescription());
        }

        responseJsonOrJsonp(serviceResult);
    }

    public void addAdminInSession(ServiceResult<AdminVo> serviceResult) {

        addAdminInSession(getRequest(), serviceResult);
    }

    public static void addAdminInSession(HttpServletRequest request, ServiceResult<AdminVo> serviceResult) {

        String ip = Utils.getIp(request);
        if (!serviceResult.isResult()) {
            HitControllerInterceptor.incrementFailLoginCount(ip);
            return;
        }

        HttpSession session = request.getSession();
        AdminVo vo = serviceResult.getReturnData();
        session.setAttribute(ADMIN_SESSION_KEY, vo);
        AdminVo buf = new AdminVo();
        buf.setPhoto(vo.getPhoto());
        buf.setUserName(vo.getUserName());
        buf.setRoleName(vo.getRoleName());
        buf.setNikeName(vo.getNikeName());
        buf.setPhoneNumber(vo.getPhoneNumber());
        session.setAttribute(ADMIN_SESSION_BUF_KEY, buf);
        session.setAttribute(AbstractInterceptor.USER_NAME, vo.getUserName());
        serviceResult.setReturnData(null);
        HitControllerInterceptor.clearFailLoginCount(ip);
    }

    @RequestMapping(value = "/getInfo")
    public void getInfo() {

        ServiceResult<AdminVo> serviceResult = new ServiceResult<>();
        serviceResult.setResult(true);
        serviceResult.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
        serviceResult.setMessage(HttpStatusEnum.SUCCESS.getDescription());

        try {

            AdminVo vo = getSessionAttribute(ADMIN_SESSION_BUF_KEY);
            serviceResult.setReturnData(vo);
            if (vo == null) {
                serviceResult.setResult(false);
                serviceResult.setResponseCode(HttpStatusEnum.LOGIN_OVER_TIME.getCode());
                serviceResult.setMessage(HttpStatusEnum.LOGIN_OVER_TIME.getDescription());
            }
        } catch (Exception e) {
            LOGGER.warn("getInfo", e);
            serviceResult.setResult(false);
            serviceResult.setResponseCode(HttpStatusEnum.SYSTEM_ERROR.getCode());
            serviceResult.setMessage(HttpStatusEnum.SYSTEM_ERROR.getDescription());
        }

        responseJsonOrJsonp(serviceResult);
    }

    @RequestMapping(value = "/getPhotoByUserName")
    public void getPhotoByUserName(AdminBo bo) {

        ServiceResult<String> serviceResult = new ServiceResult<>();
        serviceResult.setResult(true);
        serviceResult.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
        serviceResult.setMessage(HttpStatusEnum.SUCCESS.getDescription());

        try {
            serviceResult = adminService.queryPhotoByUserName(bo);
        } catch (Exception e) {
            LOGGER.warn("getPhotoByUserName", e);
            serviceResult.setResult(false);
            serviceResult.setResponseCode(HttpStatusEnum.SYSTEM_ERROR.getCode());
            serviceResult.setMessage(HttpStatusEnum.SYSTEM_ERROR.getDescription());
        }

        responseJsonOrJsonp(serviceResult);
    }

    @RequestMapping(value = "/lock")
    public void lock() {

        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setResult(true);
        serviceResult.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
        serviceResult.setMessage(HttpStatusEnum.SUCCESS.getDescription());

        try {
            AdminVo vo = getSessionAttribute(ADMIN_SESSION_BUF_KEY);
            if (vo != null) {
                vo.setLock(true);
                setSessionAttribute(ADMIN_SESSION_BUF_KEY, vo);
            } else {
                serviceResult.setResult(false);
                serviceResult.setResponseCode(HttpStatusEnum.LOGIN_OVER_TIME.getCode());
                serviceResult.setMessage(HttpStatusEnum.LOGIN_OVER_TIME.getDescription());
            }
        } catch (Exception e) {
            LOGGER.warn("lock", e);
            serviceResult.setResult(false);
            serviceResult.setResponseCode(HttpStatusEnum.SYSTEM_ERROR.getCode());
            serviceResult.setMessage(HttpStatusEnum.SYSTEM_ERROR.getDescription());
        }

        responseJsonOrJsonp(serviceResult);
    }

    @RequestMapping(value = "/lockLogin")
    public void lockLogin(String password) {

        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setResult(true);
        serviceResult.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
        serviceResult.setMessage(HttpStatusEnum.SUCCESS.getDescription());

        try {
            AdminVo vo = getSessionAttribute(ADMIN_SESSION_KEY);
            if (vo != null) {
                if (MD5Utils.isEquals(password, vo.getPassword())) {
                    vo = getSessionAttribute(ADMIN_SESSION_BUF_KEY);
                    vo.setLock(false);
                    setSessionAttribute(ADMIN_SESSION_BUF_KEY, vo);
                }
            } else {
                serviceResult.setResult(false);
                serviceResult.setResponseCode(HttpStatusEnum.LOGIN_OVER_TIME.getCode());
                serviceResult.setMessage(HttpStatusEnum.LOGIN_OVER_TIME.getDescription());
            }
        } catch (Exception e) {
            LOGGER.warn("lockLogin", e);
            serviceResult.setResult(false);
            serviceResult.setResponseCode(HttpStatusEnum.SYSTEM_ERROR.getCode());
            serviceResult.setMessage(HttpStatusEnum.SYSTEM_ERROR.getDescription());
        }

        responseJsonOrJsonp(serviceResult);
    }

    @RequestMapping(value = "/logout")
    public void logout() {

        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setResult(true);
        serviceResult.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
        serviceResult.setMessage(HttpStatusEnum.SUCCESS.getDescription());

        try {
            getSession().invalidate();
        } catch (Exception e) {
            LOGGER.warn("logout", e);
            serviceResult.setResult(false);
            serviceResult.setResponseCode(HttpStatusEnum.SYSTEM_ERROR.getCode());
            serviceResult.setMessage(HttpStatusEnum.SYSTEM_ERROR.getDescription());
        }

        responseJsonOrJsonp(serviceResult);
    }
}
