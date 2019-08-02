package com.github.chenhao96.dao;

import com.github.chenhao96.model.bo.AdminBo;
import com.github.chenhao96.model.po.Users;
import com.github.chenhao96.model.vo.AdminVo;
import com.github.chenhao96.mybatis.SqlMapper;

import java.util.List;

@SqlMapper
public interface UsersMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteByUserName(String userName);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    Users selectByUserName(String userName);

    int updateByPrimaryKeySelective(Users record);

    String selectUserPasswordById(Integer id);

    String selectUserPhotoByUserName(String userName);

    List<AdminVo> userList();

    long countList(AdminBo bo);

    List<AdminVo> pageList(AdminBo bo);

    int batchDelete(Integer[] ids);
}