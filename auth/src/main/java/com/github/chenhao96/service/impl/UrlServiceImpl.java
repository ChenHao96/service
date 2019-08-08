package com.github.chenhao96.service.impl;

import com.github.chenhao96.adaptor.UrlAdaptor;
import com.github.chenhao96.model.bo.RoleUrlBo;
import com.github.chenhao96.model.enumeration.HttpStatusEnum;
import com.github.chenhao96.model.po.RoleUrl;
import com.github.chenhao96.model.vo.PageVo;
import com.github.chenhao96.model.vo.RoleUrlVo;
import com.github.chenhao96.service.ServiceResult;
import com.github.chenhao96.service.UrlService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UrlServiceImpl implements UrlService {

    @Resource
    private UrlAdaptor urlAdaptor;

    @Override
    public ServiceResult<PageVo<RoleUrlVo>> pageUrlList(RoleUrlBo bo) {
        ServiceResult<PageVo<RoleUrlVo>> result = new ServiceResult<>();
        result.setResult(true);
        result.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
        result.setMessage(HttpStatusEnum.SUCCESS.getDescription());
        bo.process();
        PageVo<RoleUrlVo> pageVo = new PageVo<>();
        pageVo.setTotal(urlAdaptor.countList(bo));
        if(pageVo.getTotal() > 0){
            pageVo.setRows(urlAdaptor.pageList(bo));
        }
        result.setReturnData(pageVo);
        return result;
    }

    @Override
    public ServiceResult<List<RoleUrlVo>> urlList(Integer roleNameId) {
        ServiceResult<List<RoleUrlVo>> result = new ServiceResult<>();
        result.setResult(true);
        result.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
        result.setMessage(HttpStatusEnum.SUCCESS.getDescription());
        result.setReturnData(urlAdaptor.urlList(roleNameId));
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult batchDelete(RoleUrlBo[] array) {

        ServiceResult result = new ServiceResult();
        result.setResult(false);
        result.setResponseCode(HttpStatusEnum.PARAM_FAULT.getCode());
        result.setMessage(HttpStatusEnum.PARAM_FAULT.getDescription());

        if (array == null || array.length == 0) return result;
        List<Integer> list = new ArrayList<>(array.length);
        for (RoleUrlBo anArray : array) {
            if (anArray != null && anArray.getId() > 0) {
                list.add(anArray.getId());
            }
        }

        if (urlAdaptor.batchDelete(list.toArray(new Integer[list.size()])) > 0) {
            result.setResult(true);
            result.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
            result.setMessage(HttpStatusEnum.SUCCESS.getDescription());
        }

        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult saveOrUpdateUrl(RoleUrlBo bo) {

        ServiceResult result = new ServiceResult();
        result.setResult(false);
        result.setResponseCode(HttpStatusEnum.PARAM_FAULT.getCode());
        result.setMessage(HttpStatusEnum.PARAM_FAULT.getDescription());

        RoleUrl record = new RoleUrl();
        try {
            BeanUtils.copyProperties(bo, record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        int ret;
        if (record.getId() == null) {
            record.setCreateAt(record.getUpdateAt());
            ret = urlAdaptor.insertSelective(record);
        } else {
            ret = urlAdaptor.updateByPrimaryKeySelective(record);
        }

        if (ret == 1) {
            result.setResult(true);
            result.setResponseCode(HttpStatusEnum.SUCCESS.getCode());
            result.setMessage(HttpStatusEnum.SUCCESS.getDescription());
        }

        return result;
    }
}
