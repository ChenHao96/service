package com.github.chenhao96.adaptor;

import com.github.chenhao96.model.bo.RoleNameBo;
import com.github.chenhao96.model.po.RoleName;
import com.github.chenhao96.model.vo.RoleNameVo;

import java.util.List;

public interface RoleNameAdaptor {

    int deleteByPrimaryKey(Integer id);

    int insert(RoleName record);

    List<RoleName> selectByCreateAt(Integer createAt);

    int updateByPrimaryKey(RoleName record);

    String queryRoleNameById(Integer id);

    long countList(RoleNameBo bo);

    List<RoleNameVo> pageList(RoleNameBo bo);

    List<RoleNameVo> roleList();

    int batchDelete(Integer[] integers);
}
