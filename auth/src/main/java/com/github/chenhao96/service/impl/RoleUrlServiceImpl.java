package com.github.chenhao96.service.impl;

import com.github.chenhao96.adaptor.RolePermissionAdaptor;
import com.github.chenhao96.model.bo.RolePermissionBo;
import com.github.chenhao96.model.enumeration.HttpStatusEnum;
import com.github.chenhao96.model.po.RolePermission;
import com.github.chenhao96.model.vo.PageVo;
import com.github.chenhao96.model.vo.RolePermissionVo;
import com.github.chenhao96.service.RoleUrlService;
import com.github.chenhao96.service.ServiceResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleUrlServiceImpl implements RoleUrlService {

    @Resource
    private RolePermissionAdaptor rolePermissionAdaptor;

    @Override
    public ServiceResult<PageVo<RolePermissionVo>> pageRoleUrlList(RolePermissionBo bo) {

        ServiceResult<PageVo<RolePermissionVo>> result = new ServiceResult<>();
        result.setResult(true);
        result.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
        result.setMessage(HttpStatusEnum.SUCCESS.getDescription());

        bo.process();
        PageVo<RolePermissionVo> pageVo = new PageVo<>();
        pageVo.setTotal(rolePermissionAdaptor.countList(bo));
        if(pageVo.getTotal() > 0){
            pageVo.setRows(rolePermissionAdaptor.pageList(bo));
        }

        result.setReturnData(pageVo);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult batchDelete(RolePermissionBo[] array) {

        ServiceResult result = new ServiceResult();
        result.setResult(false);
        result.setResponseCode(HttpStatusEnum.PARAM_FAULT.getCode());
        result.setMessage(HttpStatusEnum.PARAM_FAULT.getDescription());

        if (array == null || array.length == 0) return result;
        List<Integer> list = new ArrayList<>(array.length);
        for (RolePermissionBo anArray : array) {
            if (anArray != null && anArray.getId() > 0) {
                list.add(anArray.getId());
            }
        }

        if (rolePermissionAdaptor.batchDelete(list.toArray(new Integer[list.size()])) > 0) {
            result.setResult(true);
            result.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
            result.setMessage(HttpStatusEnum.SUCCESS.getDescription());
        }

        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult savePermission(RolePermissionBo bo) {

        ServiceResult result = new ServiceResult();
        result.setResult(false);
        result.setResponseCode(HttpStatusEnum.PARAM_FAULT.getCode());
        result.setMessage(HttpStatusEnum.PARAM_FAULT.getDescription());

        List<RolePermission> list = null;
        List<Integer> existList = rolePermissionAdaptor.queryExistPermissionId(bo.getRoleNameId());
        Integer[] roleIds = bo.getRoleUrls();
        if (roleIds != null) {
            list = new ArrayList<>(roleIds.length);
            for (Integer urlId : roleIds) {
                if (urlId == null || (existList != null && existList.contains(urlId))) continue;
                RolePermission permission = new RolePermission();
                permission.setRoleNameId(bo.getRoleNameId());
                permission.setRoleUrlId(urlId);
                list.add(permission);
            }
        } else {
            if (bo.getRoleUrlId() != null) {
                list = new ArrayList<>(1);
                RolePermission permission = new RolePermission();
                permission.setRoleNameId(bo.getRoleNameId());
                permission.setRoleUrlId(bo.getRoleUrlId());
                list.add(permission);
            }
        }

        int ret = 0;
        if (list != null && list.size() > 0) {
            for (RolePermission permission : list) {
                permission.setUpdateAt(bo.getUpdateAt());
                permission.setCreateAt(permission.getUpdateAt());
                ret += rolePermissionAdaptor.insert(permission);
            }
        }

        if (ret > 0) {
            result.setResult(true);
            result.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
            result.setMessage(HttpStatusEnum.SUCCESS.getDescription());
        } else if (roleIds != null && roleIds.length > 0) {
            result.setResponseCode(HttpStatusEnum.REPEAT_AUTHORIZATION.getCode());
            result.setMessage(HttpStatusEnum.REPEAT_AUTHORIZATION.getDescription());
        }

        return result;
    }
}
