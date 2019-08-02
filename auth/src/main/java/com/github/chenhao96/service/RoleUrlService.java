package com.github.chenhao96.service;

import com.github.chenhao96.model.bo.RolePermissionBo;
import com.github.chenhao96.model.vo.PageVo;
import com.github.chenhao96.model.vo.RolePermissionVo;

public interface RoleUrlService {

    ServiceResult<PageVo<RolePermissionVo>> pageRoleUrlList(RolePermissionBo bo);

    ServiceResult batchDelete(RolePermissionBo[] array);

    ServiceResult savePermission(RolePermissionBo bo);
}
