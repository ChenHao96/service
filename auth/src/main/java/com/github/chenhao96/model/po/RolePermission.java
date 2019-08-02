package com.github.chenhao96.model.po;

public class RolePermission {

    private Integer id;

    private Integer roleNameId;

    private Integer roleUrlId;

    private Integer updateAt;

    private Integer createAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleNameId() {
        return roleNameId;
    }

    public void setRoleNameId(Integer roleNameId) {
        this.roleNameId = roleNameId;
    }

    public Integer getRoleUrlId() {
        return roleUrlId;
    }

    public void setRoleUrlId(Integer roleUrlId) {
        this.roleUrlId = roleUrlId;
    }

    public Integer getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Integer updateAt) {
        this.updateAt = updateAt;
    }

    public Integer getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Integer createAt) {
        this.createAt = createAt;
    }
}