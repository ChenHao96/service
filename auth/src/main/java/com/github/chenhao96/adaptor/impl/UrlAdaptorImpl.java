package com.github.chenhao96.adaptor.impl;

import com.github.chenhao96.adaptor.UrlAdaptor;
import com.github.chenhao96.dao.RoleUrlMapper;
import com.github.chenhao96.model.bo.RoleUrlBo;
import com.github.chenhao96.model.po.RoleUrl;
import com.github.chenhao96.model.vo.RoleUrlVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UrlAdaptorImpl implements UrlAdaptor {

    @Resource
    private RoleUrlMapper roleUrlMapper;

    @Override
    public long countList(RoleUrlBo bo) {
        return roleUrlMapper.countList(bo);
    }

    @Override
    public List<RoleUrlVo> pageList(RoleUrlBo bo) {
        return roleUrlMapper.pageList(bo);
    }

    @Override
    public List<RoleUrlVo> urlList(Integer roleNameId) {
        return roleUrlMapper.urlList(roleNameId);
    }

    @Override
    public int batchDelete(Integer[] integers) {
        return roleUrlMapper.batchDelete(integers);
    }

    @Override
    public int insertSelective(RoleUrl record) {
        return roleUrlMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(RoleUrl record) {
        return roleUrlMapper.updateByPrimaryKeySelective(record);
    }
}
