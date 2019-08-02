package com.github.chenhao96.service;

import com.github.chenhao96.model.bo.RoleNameBo;
import com.github.chenhao96.model.vo.PageVo;
import com.github.chenhao96.model.vo.RoleNameVo;

import java.util.List;

public interface RoleNameService {

    ServiceResult<PageVo<RoleNameVo>> pageRoleList(RoleNameBo bo);

    ServiceResult<List<RoleNameVo>> roleList();

    ServiceResult batchDelete(RoleNameBo[] array);

    ServiceResult saveOrUpdateRole(RoleNameBo bo);
}
