package com.github.chenhao96.service;

import com.github.chenhao96.model.bo.AdminBo;
import com.github.chenhao96.model.vo.AdminVo;
import com.github.chenhao96.model.vo.PageVo;

import java.util.List;

public interface AdminService {

    ServiceResult<AdminVo> checkAdminLogin(AdminBo bo);

    ServiceResult<AdminVo> updateUserInfo(AdminBo bo);

    ServiceResult<String> queryPhotoByUserName(AdminBo bo);

    ServiceResult<List<AdminVo>> userList();

    ServiceResult<PageVo<AdminVo>> pageAdminList(AdminBo bo);

    ServiceResult batchDelete(AdminBo[] array);

    ServiceResult saveOrUpdateAdmin(AdminBo bo);
}
