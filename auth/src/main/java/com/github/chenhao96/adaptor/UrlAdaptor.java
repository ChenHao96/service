package com.github.chenhao96.adaptor;

import com.github.chenhao96.model.bo.RoleUrlBo;
import com.github.chenhao96.model.po.RoleUrl;
import com.github.chenhao96.model.vo.RoleUrlVo;

import java.util.List;

public interface UrlAdaptor {

    long countList(RoleUrlBo bo);

    List<RoleUrlVo> pageList(RoleUrlBo bo);

    List<RoleUrlVo> urlList(Integer roleNameId);

    int batchDelete(Integer[] integers);

    int insertSelective(RoleUrl record);

    int updateByPrimaryKeySelective(RoleUrl record);
}
