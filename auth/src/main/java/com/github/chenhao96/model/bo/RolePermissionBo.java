package com.github.chenhao96.model.bo;

import java.util.Arrays;

public class RolePermissionBo extends PageParamBo {

    private Integer id;

    private Integer roleNameId;

    private Integer roleUrlId;

    private Integer[] roleUrls;

    private Integer updateAt;

    private Integer createAt;

    private String updateAtStr;

    private String createAtStr;

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

    public String getUpdateAtStr() {
        return updateAtStr;
    }

    public void setUpdateAtStr(String updateAtStr) {
        this.updateAtStr = updateAtStr;
    }

    public String getCreateAtStr() {
        return createAtStr;
    }

    public void setCreateAtStr(String createAtStr) {
        this.createAtStr = createAtStr;
    }

    public Integer[] getRoleUrls() {
        return roleUrls;
    }

    public void setRoleUrls(Integer[] roleUrls) {
        this.roleUrls = roleUrls;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RolePermissionBo{");
        sb.append("id=").append(id);
        sb.append(", roleNameId=").append(roleNameId);
        sb.append(", roleUrlId=").append(roleUrlId);
        sb.append(", roleUrls=").append(Arrays.toString(roleUrls));
        sb.append(", updateAt=").append(updateAt);
        sb.append(", createAt=").append(createAt);
        sb.append(", updateAtStr='").append(updateAtStr).append('\'');
        sb.append(", createAtStr='").append(createAtStr).append('\'');
        sb.append(", superString='").append(super.toString()).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
