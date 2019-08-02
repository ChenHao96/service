package com.github.chenhao96.service.impl;

import com.github.chenhao96.adaptor.RoleNameAdaptor;
import com.github.chenhao96.adaptor.RolePermissionAdaptor;
import com.github.chenhao96.adaptor.UsersAdaptor;
import com.github.chenhao96.encrypt.MD5Utils;
import com.github.chenhao96.model.bo.AdminBo;
import com.github.chenhao96.model.enumeration.HttpStatusEnum;
import com.github.chenhao96.model.po.Users;
import com.github.chenhao96.model.vo.AdminVo;
import com.github.chenhao96.model.vo.PageVo;
import com.github.chenhao96.service.AdminService;
import com.github.chenhao96.service.ServiceResult;
import com.github.chenhao96.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private UsersAdaptor usersAdaptor;

    @Resource
    private RolePermissionAdaptor rolePermissionAdaptor;

    @Resource
    private RoleNameAdaptor roleNameAdaptor;

    @Override
    public ServiceResult<AdminVo> checkAdminLogin(AdminBo bo) {
        ServiceResult<AdminVo> result = new ServiceResult<>();
        result.setResult(false);
        result.setResponseCode(HttpStatusEnum.USERNAME_OR_PASSWORD_FAULT.getCode());
        result.setMessage(HttpStatusEnum.USERNAME_OR_PASSWORD_FAULT.getDescription());
        Users user = usersAdaptor.selectByUserName(bo.getUserName());
        if (user != null && MD5Utils.isEquals(bo.getPassword(), user.getPassword())) {
            result.setResult(true);
            result.setReturnData(getAdminVo(user));
            result.setResponseCode(HttpStatusEnum.LOGIN_SUCCESS.getCode());
            result.setMessage(HttpStatusEnum.LOGIN_SUCCESS.getDescription());
        }
        return result;
    }

    private AdminVo getAdminVo(Users user) {
        AdminVo vo = new AdminVo();
        try {
            BeanUtils.copyProperties(user, vo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        vo.setUrls(rolePermissionAdaptor.queryUrlsByUserId(user.getId()));
        vo.setRoleName(roleNameAdaptor.queryRoleNameById(user.getRoleNameId()));
        return vo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult<AdminVo> updateUserInfo(AdminBo bo) {

        ServiceResult<AdminVo> result = new ServiceResult<>();
        result.setResult(false);
        result.setResponseCode(HttpStatusEnum.INFO_UPDATE_FAULT.getCode());
        result.setMessage(HttpStatusEnum.INFO_UPDATE_FAULT.getDescription());

        if (StringUtils.isNotEmpty(bo.getPassword())) {
            if (MD5Utils.isEquals(bo.getPassword(), usersAdaptor.selectUserPasswordById(bo.getId()))) {
                if (StringUtils.isNotEmpty(bo.getChangePassword())) {
                    if (bo.getChangePassword().equals(bo.getConfirmPassword())) {
                        bo.setPassword(MD5Utils.getMD5Hex(bo.getChangePassword()));
                    } else {
                        result.setResponseCode(HttpStatusEnum.CONFIRM_PASSWORD_NOT_SAME.getCode());
                        result.setMessage(HttpStatusEnum.CONFIRM_PASSWORD_NOT_SAME.getDescription());
                        return result;
                    }
                }
            } else {
                result.setResponseCode(HttpStatusEnum.LOGIN_PASSWORD_FAULT.getCode());
                result.setMessage(HttpStatusEnum.LOGIN_PASSWORD_FAULT.getDescription());
                return result;
            }
        } else {
            bo.setPassword(null);
        }

        Users record = new Users();
        try {
            BeanUtils.copyProperties(bo, record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (usersAdaptor.updateByPrimaryKeySelective(record) == 1) {
            if (record.getId().equals(record.getUpdateAt())) {
                result.setReturnData(getAdminVo(usersAdaptor.selectByPrimaryKey(bo.getId())));
            }
            result.setResult(true);
            result.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
            result.setMessage(HttpStatusEnum.SUCCESS.getDescription());
        }

        return result;
    }

    @Override
    public ServiceResult<String> queryPhotoByUserName(AdminBo bo) {

        ServiceResult<String> result = new ServiceResult<>();
        result.setResult(true);
        result.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
        result.setMessage(HttpStatusEnum.SUCCESS.getDescription());

        String photo = usersAdaptor.selectUserPhotoByUserName(bo.getUserName());
        if (StringUtils.isNotEmpty(photo)) {
            result.setReturnData(photo);
        }

        return result;
    }

    @Override
    public ServiceResult<List<AdminVo>> userList() {
        ServiceResult<List<AdminVo>> result = new ServiceResult<>();
        result.setResult(true);
        result.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
        result.setMessage(HttpStatusEnum.SUCCESS.getDescription());
        result.setReturnData(usersAdaptor.userList());
        return result;
    }

    @Override
    public ServiceResult<PageVo<AdminVo>> pageAdminList(AdminBo bo) {

        ServiceResult<PageVo<AdminVo>> result = new ServiceResult<>();
        result.setResult(true);
        result.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
        result.setMessage(HttpStatusEnum.SUCCESS.getDescription());
        bo.process();
        PageVo<AdminVo> pageVo = new PageVo<>();
        pageVo.setTotal(usersAdaptor.countList(bo));
        if(pageVo.getTotal() > 0){
            pageVo.setRows(usersAdaptor.pageList(bo));
        }
        result.setReturnData(pageVo);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult batchDelete(AdminBo[] array) {

        ServiceResult result = new ServiceResult();
        result.setResult(false);
        result.setResponseCode(HttpStatusEnum.PARAM_FAULT.getCode());
        result.setMessage(HttpStatusEnum.PARAM_FAULT.getDescription());

        if (array == null || array.length == 0) return result;
        List<Integer> list = new ArrayList<>(array.length);
        for (AdminBo anArray : array) {
            if (anArray != null && anArray.getId() > 0) {
                list.add(anArray.getId());
            }
        }

        if (usersAdaptor.batchDelete(list.toArray(new Integer[list.size()])) > 0) {
            result.setResult(true);
            result.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
            result.setMessage(HttpStatusEnum.SUCCESS.getDescription());
        }

        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult saveOrUpdateAdmin(AdminBo bo) {

        ServiceResult result = new ServiceResult();
        result.setResult(false);
        result.setResponseCode(HttpStatusEnum.PARAM_FAULT.getCode());
        result.setMessage(HttpStatusEnum.PARAM_FAULT.getDescription());

        if (StringUtils.isNotEmpty(bo.getPassword())) {
            if (StringUtils.isNotEmpty(bo.getConfirmPassword())) {
                if (bo.getPassword().equals(bo.getConfirmPassword())) {
                    bo.setPassword(MD5Utils.getMD5Hex(bo.getPassword()));
                } else {
                    result.setResponseCode(HttpStatusEnum.CONFIRM_PASSWORD_NOT_SAME.getCode());
                    result.setMessage(HttpStatusEnum.CONFIRM_PASSWORD_NOT_SAME.getDescription());
                    return result;
                }
            } else {
                bo.setPassword(null);
            }
        } else {
            bo.setPassword(null);
        }

        Users record = new Users();
        try {
            BeanUtils.copyProperties(bo, record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        int ret;
        if (record.getId() == null) {
            record.setCreateAt(record.getUpdateAt());
            ret = usersAdaptor.insertSelective(record);
        } else {
            ret = usersAdaptor.updateByPrimaryKeySelective(record);
        }

        if (ret == 1) {
            result.setResult(true);
            result.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
            result.setMessage(HttpStatusEnum.SUCCESS.getDescription());
        }

        return result;
    }
}
