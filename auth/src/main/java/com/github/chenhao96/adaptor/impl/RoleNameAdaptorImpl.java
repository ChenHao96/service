package com.github.chenhao96.adaptor.impl;

import com.github.chenhao96.adaptor.RoleNameAdaptor;
import com.github.chenhao96.dao.RoleNameMapper;
import com.github.chenhao96.model.bo.RoleNameBo;
import com.github.chenhao96.model.po.RoleName;
import com.github.chenhao96.model.vo.RoleNameVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class RoleNameAdaptorImpl implements RoleNameAdaptor {

    @Resource
    private RoleNameMapper roleNameMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return roleNameMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RoleName record) {
        return roleNameMapper.insert(record);
    }

    @Override
    public List<RoleName> selectByCreateAt(Integer createAt) {
        return roleNameMapper.selectByCreateAt(createAt);
    }

    @Override
    public int updateByPrimaryKey(RoleName record) {
        return roleNameMapper.updateByPrimaryKey(record);
    }

    @Override
    public String queryRoleNameById(Integer id) {
        return roleNameMapper.queryRoleNameById(id);
    }

    @Override
    public long countList(RoleNameBo bo) {
        return roleNameMapper.countList(bo);
    }

    @Override
    public List<RoleNameVo> pageList(RoleNameBo bo) {
        return roleNameMapper.pageList(bo);
    }

    @Override
    public List<RoleNameVo> roleList() {
        return roleNameMapper.roleList();
    }

    @Override
    public int batchDelete(Integer[] integers) {
        return roleNameMapper.batchDelete(integers);
    }
}
