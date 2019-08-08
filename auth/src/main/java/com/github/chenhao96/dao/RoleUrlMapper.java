package com.github.chenhao96.dao;

import com.github.chenhao96.model.bo.RoleUrlBo;
import com.github.chenhao96.model.po.RoleUrl;
import com.github.chenhao96.model.vo.RoleUrlVo;
import com.github.chenhao96.mybatis.SqlMapper;

import java.util.List;

@SqlMapper
public interface RoleUrlMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(RoleUrl record);

    int updateByPrimaryKeySelective(RoleUrl record);

    List<RoleUrlVo> urlList(Integer roleNameId);

    long countList(RoleUrlBo bo);

    List<RoleUrlVo> pageList(RoleUrlBo bo);

    int batchDelete(Integer[] integers);
}