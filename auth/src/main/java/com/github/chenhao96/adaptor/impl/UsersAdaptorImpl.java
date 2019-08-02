package com.github.chenhao96.adaptor.impl;

import com.github.chenhao96.adaptor.UsersAdaptor;
import com.github.chenhao96.dao.UsersMapper;
import com.github.chenhao96.model.bo.AdminBo;
import com.github.chenhao96.model.po.Users;
import com.github.chenhao96.model.vo.AdminVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UsersAdaptorImpl implements UsersAdaptor {

    @Resource
    private UsersMapper usersMapper;

    @Override
    public Users selectByUserName(String userName) {
        return usersMapper.selectByUserName(userName);
    }

    @Override
    public int updateByPrimaryKeySelective(Users record) {
        return usersMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int insertSelective(Users record) {
        return usersMapper.insertSelective(record);
    }

    @Override
    public Users selectByPrimaryKey(Integer id) {
        return usersMapper.selectByPrimaryKey(id);
    }

    @Override
    public String selectUserPasswordById(Integer id) {
        return usersMapper.selectUserPasswordById(id);
    }

    @Override
    public String selectUserPhotoByUserName(String userName) {
        return usersMapper.selectUserPhotoByUserName(userName);
    }

    @Override
    public List<AdminVo> userList() {
        return usersMapper.userList();
    }

    @Override
    public long countList(AdminBo bo) {
        return usersMapper.countList(bo);
    }

    @Override
    public List<AdminVo> pageList(AdminBo bo) {
        return usersMapper.pageList(bo);
    }

    @Override
    public int batchDelete(Integer[] ids) {
        return usersMapper.batchDelete(ids);
    }
}
