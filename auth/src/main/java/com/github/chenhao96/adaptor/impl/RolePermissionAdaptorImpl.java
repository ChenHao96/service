package com.github.chenhao96.adaptor.impl;

import com.github.chenhao96.adaptor.RolePermissionAdaptor;
import com.github.chenhao96.dao.RolePermissionMapper;
import com.github.chenhao96.model.bo.RolePermissionBo;
import com.github.chenhao96.model.po.RolePermission;
import com.github.chenhao96.model.vo.RolePermissionVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Repository
public class RolePermissionAdaptorImpl implements RolePermissionAdaptor {

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return rolePermissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RolePermission record) {
        return rolePermissionMapper.insert(record);
    }

    @Override
    public RolePermission selectByPrimaryKey(Integer id) {
        return rolePermissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public Set<String> queryUrlsByUserId(Integer userID) {
        return rolePermissionMapper.queryUrlsByUserId(userID);
    }

    @Override
    public long countList(RolePermissionBo bo) {
        return rolePermissionMapper.countList(bo);
    }

    @Override
    public List<RolePermissionVo> pageList(RolePermissionBo bo) {
        return rolePermissionMapper.pageList(bo);
    }

    @Override
    public int batchDelete(Integer[] integers) {
        return rolePermissionMapper.batchDelete(integers);
    }

    @Override
    public List<Integer> queryExistPermissionId(Integer roleNameId) {
        return rolePermissionMapper.queryExistPermissionId(roleNameId);
    }

    @Override
    public int batchDelete4RoleName(List<Integer> list) {
        return rolePermissionMapper.batchDelete4RoleName(list);
    }
}
