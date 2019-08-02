package com.github.chenhao96.service.impl;

import com.github.chenhao96.adaptor.RoleNameAdaptor;
import com.github.chenhao96.adaptor.RolePermissionAdaptor;
import com.github.chenhao96.model.bo.RoleNameBo;
import com.github.chenhao96.model.enumeration.HttpStatusEnum;
import com.github.chenhao96.model.po.RoleName;
import com.github.chenhao96.model.vo.PageVo;
import com.github.chenhao96.model.vo.RoleNameVo;
import com.github.chenhao96.service.RoleNameService;
import com.github.chenhao96.service.ServiceResult;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleNameServiceImpl implements RoleNameService {

    @Resource
    private RoleNameAdaptor roleNameAdaptor;

    @Resource
    private RolePermissionAdaptor rolePermissionAdaptor;

    @Override
    public ServiceResult<PageVo<RoleNameVo>> pageRoleList(RoleNameBo bo) {
        ServiceResult<PageVo<RoleNameVo>> result = new ServiceResult<>();
        result.setResult(true);
        result.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
        result.setMessage(HttpStatusEnum.SUCCESS.getDescription());
        bo.process();
        PageVo<RoleNameVo> pageVo = new PageVo<>();
        pageVo.setTotal(roleNameAdaptor.countList(bo));
        if(pageVo.getTotal() > 0){
            pageVo.setRows(roleNameAdaptor.pageList(bo));
        }
        result.setReturnData(pageVo);
        return result;
    }

    @Override
    public ServiceResult<List<RoleNameVo>> roleList() {
        ServiceResult<List<RoleNameVo>> result = new ServiceResult<>();
        result.setResult(true);
        result.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
        result.setMessage(HttpStatusEnum.SUCCESS.getDescription());
        result.setReturnData(roleNameAdaptor.roleList());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult batchDelete(RoleNameBo[] array) {

        ServiceResult result = new ServiceResult();
        result.setResult(false);
        result.setResponseCode(HttpStatusEnum.PARAM_FAULT.getCode());
        result.setMessage(HttpStatusEnum.PARAM_FAULT.getDescription());

        if (array == null || array.length == 0) return result;
        List<Integer> list = new ArrayList<>(array.length);
        for (RoleNameBo anArray : array) {
            if (anArray != null && anArray.getId() > 0) {
                list.add(anArray.getId());
            }
        }

        rolePermissionAdaptor.batchDelete4RoleName(list);
        if (roleNameAdaptor.batchDelete(list.toArray(new Integer[list.size()])) > 0) {
            result.setResult(true);
            result.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
            result.setMessage(HttpStatusEnum.SUCCESS.getDescription());
        }

        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult saveOrUpdateRole(RoleNameBo bo) {

        ServiceResult result = new ServiceResult();
        result.setResult(false);
        result.setResponseCode(HttpStatusEnum.PARAM_FAULT.getCode());
        result.setMessage(HttpStatusEnum.PARAM_FAULT.getDescription());

        RoleName record = new RoleName();
        try {
            BeanUtils.copyProperties(bo, record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        int ret;
        if (record.getId() == null) {
            record.setCreateAt(record.getUpdateAt());
            ret = roleNameAdaptor.insert(record);
        } else {
            ret = roleNameAdaptor.updateByPrimaryKey(record);
        }

        if (ret == 1) {
            result.setResult(true);
            result.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
            result.setMessage(HttpStatusEnum.SUCCESS.getDescription());
        }

        return result;
    }
}
