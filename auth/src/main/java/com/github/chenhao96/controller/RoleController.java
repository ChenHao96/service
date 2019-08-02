package com.github.chenhao96.controller;

import com.github.chenhao96.model.bo.RoleNameBo;
import com.github.chenhao96.model.enumeration.HttpStatusEnum;
import com.github.chenhao96.model.vo.AdminVo;
import com.github.chenhao96.model.vo.PageVo;
import com.github.chenhao96.model.vo.RoleNameVo;
import com.github.chenhao96.service.RoleNameService;
import com.github.chenhao96.service.ServiceResult;
import com.github.chenhao96.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @Resource
    private RoleNameService roleNameService;

    @RequestMapping("/list")
    public void list(RoleNameBo bo) {

        ServiceResult<PageVo<RoleNameVo>> serviceResult = new ServiceResult<>();
        serviceResult.setResult(true);
        serviceResult.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
        serviceResult.setMessage(HttpStatusEnum.SUCCESS.getDescription());

        try {
            serviceResult = roleNameService.pageRoleList(bo);
        } catch (Exception e) {
            LOGGER.warn("list", e);
            serviceResult.setResult(false);
            serviceResult.setResponseCode(HttpStatusEnum.SYSTEM_ERROR.getCode());
            serviceResult.setMessage(HttpStatusEnum.SYSTEM_ERROR.getDescription());
        }

        responseJsonOrJsonp(serviceResult);
    }

    @RequestMapping("/roleList")
    public void roleList() {

        ServiceResult<List<RoleNameVo>> serviceResult = new ServiceResult<>();
        serviceResult.setResult(true);
        serviceResult.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
        serviceResult.setMessage(HttpStatusEnum.SUCCESS.getDescription());

        try {
            serviceResult = roleNameService.roleList();
        } catch (Exception e) {
            LOGGER.warn("roleList", e);
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
            RoleNameBo[] array = JsonUtils.jsonStr2Object(data, RoleNameBo[].class);
            serviceResult = roleNameService.batchDelete(array);
        } catch (Exception e) {
            LOGGER.warn("batchDelete", e);
            serviceResult.setResult(false);
            serviceResult.setResponseCode(HttpStatusEnum.SYSTEM_ERROR.getCode());
            serviceResult.setMessage(HttpStatusEnum.SYSTEM_ERROR.getDescription());
        }

        responseJsonOrJsonp(serviceResult);
    }

    @RequestMapping("/saveOrUpdate")
    public void saveOrUpdate(RoleNameBo bo) {

        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setResult(true);
        serviceResult.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
        serviceResult.setMessage(HttpStatusEnum.SUCCESS.getDescription());

        try {
            AdminVo vo = getSessionAttribute(UsersController.ADMIN_SESSION_KEY);
            bo.setCreateAt(null);
            bo.setUpdateAt(vo.getId());
            serviceResult = roleNameService.saveOrUpdateRole(bo);
        } catch (Exception e) {
            LOGGER.warn("saveOrUpdate", e);
            serviceResult.setResult(false);
            serviceResult.setResponseCode(HttpStatusEnum.SYSTEM_ERROR.getCode());
            serviceResult.setMessage(HttpStatusEnum.SYSTEM_ERROR.getDescription());
        }

        responseJsonOrJsonp(serviceResult);
    }
}
