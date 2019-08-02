package com.github.chenhao96.controller;

import com.github.chenhao96.model.bo.RoleUrlBo;
import com.github.chenhao96.model.enumeration.HttpStatusEnum;
import com.github.chenhao96.model.vo.AdminVo;
import com.github.chenhao96.model.vo.PageVo;
import com.github.chenhao96.model.vo.RoleUrlVo;
import com.github.chenhao96.service.ServiceResult;
import com.github.chenhao96.service.UrlService;
import com.github.chenhao96.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/url")
public class UrlController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrlController.class);

    @Resource
    private UrlService urlService;

    @RequestMapping("/list")
    public void list(RoleUrlBo bo) {

        ServiceResult<PageVo<RoleUrlVo>> serviceResult = new ServiceResult<>();
        serviceResult.setResult(true);
        serviceResult.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
        serviceResult.setMessage(HttpStatusEnum.SUCCESS.getDescription());

        try {
            serviceResult = urlService.pageUrlList(bo);
        } catch (Exception e) {
            LOGGER.warn("list", e);
            serviceResult.setResult(false);
            serviceResult.setResponseCode(HttpStatusEnum.SYSTEM_ERROR.getCode());
            serviceResult.setMessage(HttpStatusEnum.SYSTEM_ERROR.getDescription());
        }

        responseJsonOrJsonp(serviceResult);
    }

    @RequestMapping("/urlList")
    public void urlList() {

        ServiceResult<List<RoleUrlVo>> serviceResult = new ServiceResult<>();
        serviceResult.setResult(true);
        serviceResult.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
        serviceResult.setMessage(HttpStatusEnum.SUCCESS.getDescription());

        try {
            serviceResult = urlService.urlList();
        } catch (Exception e) {
            LOGGER.warn("urlList", e);
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
            RoleUrlBo[] array = JsonUtils.jsonStr2Object(data, RoleUrlBo[].class);
            serviceResult = urlService.batchDelete(array);
        } catch (Exception e) {
            LOGGER.warn("batchDelete", e);
            serviceResult.setResult(false);
            serviceResult.setResponseCode(HttpStatusEnum.SYSTEM_ERROR.getCode());
            serviceResult.setMessage(HttpStatusEnum.SYSTEM_ERROR.getDescription());
        }

        responseJsonOrJsonp(serviceResult);
    }

    @RequestMapping("/saveOrUpdate")
    public void saveOrUpdate(RoleUrlBo bo) {

        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setResult(true);
        serviceResult.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
        serviceResult.setMessage(HttpStatusEnum.SUCCESS.getDescription());

        try {
            AdminVo vo = getSessionAttribute(UsersController.ADMIN_SESSION_KEY);
            bo.setCreateAt(null);
            bo.setUpdateAt(vo.getId());
            serviceResult = urlService.saveOrUpdateUrl(bo);
        } catch (Exception e) {
            LOGGER.warn("saveOrUpdate", e);
            serviceResult.setResult(false);
            serviceResult.setResponseCode(HttpStatusEnum.SYSTEM_ERROR.getCode());
            serviceResult.setMessage(HttpStatusEnum.SYSTEM_ERROR.getDescription());
        }

        responseJsonOrJsonp(serviceResult);
    }
}
