package com.github.chenhao96.controller;

import com.github.chenhao96.model.bo.RolePermissionBo;
import com.github.chenhao96.model.enumeration.HttpStatusEnum;
import com.github.chenhao96.model.vo.AdminVo;
import com.github.chenhao96.model.vo.PageVo;
import com.github.chenhao96.model.vo.RolePermissionVo;
import com.github.chenhao96.service.RoleUrlService;
import com.github.chenhao96.service.ServiceResult;
import com.github.chenhao96.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/roleUrl")
public class RoleUrlController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleUrlController.class);

    @Resource
    private RoleUrlService roleUrlService;

    @RequestMapping("/list")
    public void list(RolePermissionBo bo) {

        ServiceResult<PageVo<RolePermissionVo>> serviceResult = new ServiceResult<>();
        serviceResult.setResult(true);
        serviceResult.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
        serviceResult.setMessage(HttpStatusEnum.SUCCESS.getDescription());

        try {
            serviceResult = roleUrlService.pageRoleUrlList(bo);
        } catch (Exception e) {
            LOGGER.warn("list", e);
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
            RolePermissionBo[] array = JsonUtils.jsonStr2Object(data, RolePermissionBo[].class);
            serviceResult = roleUrlService.batchDelete(array);
        } catch (Exception e) {
            LOGGER.warn("batchDelete", e);
            serviceResult.setResult(false);
            serviceResult.setResponseCode(HttpStatusEnum.SYSTEM_ERROR.getCode());
            serviceResult.setMessage(HttpStatusEnum.SYSTEM_ERROR.getDescription());
        }

        responseJsonOrJsonp(serviceResult);
    }

    @RequestMapping("/saveRolePermission")
    public void saveRolePermission(RolePermissionBo bo) {

        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setResult(true);
        serviceResult.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
        serviceResult.setMessage(HttpStatusEnum.SUCCESS.getDescription());

        try {
            AdminVo vo = getSessionAttribute(UsersController.ADMIN_SESSION_KEY);
            bo.setCreateAt(null);
            bo.setUpdateAt(vo.getId());
            serviceResult = roleUrlService.savePermission(bo);
        } catch (Exception e) {
            LOGGER.warn("saveRolePermission", e);
            serviceResult.setResult(false);
            serviceResult.setResponseCode(HttpStatusEnum.SYSTEM_ERROR.getCode());
            serviceResult.setMessage(HttpStatusEnum.SYSTEM_ERROR.getDescription());
        }

        responseJsonOrJsonp(serviceResult);
    }
}
