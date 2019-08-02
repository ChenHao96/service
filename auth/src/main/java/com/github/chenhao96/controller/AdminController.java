package com.github.chenhao96.controller;

import com.github.chenhao96.model.bo.AdminBo;
import com.github.chenhao96.model.enumeration.HttpStatusEnum;
import com.github.chenhao96.model.vo.AdminVo;
import com.github.chenhao96.model.vo.PageVo;
import com.github.chenhao96.service.AdminService;
import com.github.chenhao96.service.ServiceResult;
import com.github.chenhao96.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Resource
    private AdminService adminService;

    @RequestMapping(value = "/updateSelfInfo")
    public void updateInfo(AdminBo bo) {

        ServiceResult<AdminVo> serviceResult = new ServiceResult<>();
        serviceResult.setResult(true);
        serviceResult.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
        serviceResult.setMessage(HttpStatusEnum.SUCCESS.getDescription());

        try {
            AdminVo vo = getSessionAttribute(UsersController.ADMIN_SESSION_KEY);
            bo.setId(vo.getId());
            bo.setUpdateAt(vo.getId());
            serviceResult = adminService.updateUserInfo(bo);
            UsersController.addAdminInSession(getRequest(), serviceResult);
        } catch (Exception e) {
            LOGGER.warn("updateInfo", e);
            serviceResult.setResult(false);
            serviceResult.setResponseCode(HttpStatusEnum.SYSTEM_ERROR.getCode());
            serviceResult.setMessage(HttpStatusEnum.SYSTEM_ERROR.getDescription());
        }

        responseJsonOrJsonp(serviceResult);
    }

    @RequestMapping("/list")
    public void list(AdminBo bo) {

        ServiceResult<PageVo<AdminVo>> serviceResult = new ServiceResult<>();
        serviceResult.setResult(true);
        serviceResult.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
        serviceResult.setMessage(HttpStatusEnum.SUCCESS.getDescription());

        try {
            serviceResult = adminService.pageAdminList(bo);
        } catch (Exception e) {
            LOGGER.warn("list", e);
            serviceResult.setResult(false);
            serviceResult.setResponseCode(HttpStatusEnum.SYSTEM_ERROR.getCode());
            serviceResult.setMessage(HttpStatusEnum.SYSTEM_ERROR.getDescription());
        }

        responseJsonOrJsonp(serviceResult);
    }

    @RequestMapping("/userList")
    public void userList() {

        ServiceResult<List<AdminVo>> serviceResult = new ServiceResult<>();
        serviceResult.setResult(true);
        serviceResult.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
        serviceResult.setMessage(HttpStatusEnum.SUCCESS.getDescription());

        try {
            serviceResult = adminService.userList();
        } catch (Exception e) {
            LOGGER.warn("userList", e);
            serviceResult.setResult(false);
            serviceResult.setResponseCode(HttpStatusEnum.SYSTEM_ERROR.getCode());
            serviceResult.setMessage(HttpStatusEnum.SYSTEM_ERROR.getDescription());
        }

        responseJsonOrJsonp(serviceResult);
    }

    @RequestMapping("/batchDelete")
    public void batchDelete(String data) {

        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setResult(true);
        serviceResult.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
        serviceResult.setMessage(HttpStatusEnum.SUCCESS.getDescription());

        try {
            AdminBo[] array = JsonUtils.jsonStr2Object(data, AdminBo[].class);
            serviceResult = adminService.batchDelete(array);
        } catch (Exception e) {
            LOGGER.warn("batchDelete", e);
            serviceResult.setResult(false);
            serviceResult.setResponseCode(HttpStatusEnum.SYSTEM_ERROR.getCode());
            serviceResult.setMessage(HttpStatusEnum.SYSTEM_ERROR.getDescription());
        }

        responseJsonOrJsonp(serviceResult);
    }

    @RequestMapping("/saveOrUpdate")
    public void saveOrUpdate(AdminBo bo) {

        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setResult(true);
        serviceResult.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
        serviceResult.setMessage(HttpStatusEnum.SUCCESS.getDescription());

        try {
            AdminVo vo = getSessionAttribute(UsersController.ADMIN_SESSION_KEY);
            bo.setCreateAt(null);
            bo.setUpdateAt(vo.getId());
            serviceResult = adminService.saveOrUpdateAdmin(bo);
        } catch (Exception e) {
            LOGGER.warn("saveOrUpdate", e);
            serviceResult.setResult(false);
            serviceResult.setResponseCode(HttpStatusEnum.SYSTEM_ERROR.getCode());
            serviceResult.setMessage(HttpStatusEnum.SYSTEM_ERROR.getDescription());
        }

        responseJsonOrJsonp(serviceResult);
    }
}
