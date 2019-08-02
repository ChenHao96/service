package com.github.chenhao96.adaptor;

import com.github.chenhao96.model.bo.AdminBo;
import com.github.chenhao96.model.po.Users;
import com.github.chenhao96.model.vo.AdminVo;

import java.util.List;

public interface UsersAdaptor {

    Users selectByUserName(String userName);

    int updateByPrimaryKeySelective(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    String selectUserPasswordById(Integer id);

    String selectUserPhotoByUserName(String userName);

    List<AdminVo> userList();

    long countList(AdminBo bo);

    List<AdminVo> pageList(AdminBo bo);

    int batchDelete(Integer[] ids);
}
