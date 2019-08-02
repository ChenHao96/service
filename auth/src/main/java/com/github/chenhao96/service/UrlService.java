package com.github.chenhao96.service;

import com.github.chenhao96.model.bo.RoleUrlBo;
import com.github.chenhao96.model.vo.PageVo;
import com.github.chenhao96.model.vo.RoleUrlVo;

import java.util.List;

public interface UrlService {

    ServiceResult<PageVo<RoleUrlVo>> pageUrlList(RoleUrlBo bo);

    ServiceResult<List<RoleUrlVo>> urlList();

    ServiceResult batchDelete(RoleUrlBo[] array);

    ServiceResult saveOrUpdateUrl(RoleUrlBo bo);
}
