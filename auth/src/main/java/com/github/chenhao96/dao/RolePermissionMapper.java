package com.github.chenhao96.dao;

import com.github.chenhao96.model.bo.RolePermissionBo;
import com.github.chenhao96.model.po.RolePermission;
import com.github.chenhao96.model.vo.RolePermissionVo;
import com.github.chenhao96.mybatis.SqlMapper;

import java.util.List;
import java.util.Set;

@SqlMapper
public interface RolePermissionMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(RolePermission record);

    RolePermission selectByPrimaryKey(Integer id);

    Set<String> queryUrlsByUserId(Integer userID);

    long countList(RolePermissionBo bo);

    List<RolePermissionVo> pageList(RolePermissionBo bo);

    int batchDelete(Integer[] integers);

    List<Integer> queryExistPermissionId(Integer roleNameId);

    int batchDelete4RoleName(List<Integer> list);
}