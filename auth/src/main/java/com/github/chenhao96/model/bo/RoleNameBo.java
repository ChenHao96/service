package com.github.chenhao96.model.bo;

public class RoleNameBo extends PageBo {

    private Integer id;

    private String roleName;

    private Integer updateAt;

    private Integer createAt;

    private String updateAtStr;

    private String createAtStr;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RoleNameBo{");
        sb.append("id=").append(id);
        sb.append(", roleName='").append(roleName).append('\'');
        sb.append(", updateAt=").append(updateAt);
        sb.append(", createAt=").append(createAt);
        sb.append(", updateAtStr='").append(updateAtStr).append('\'');
        sb.append(", createAtStr='").append(createAtStr).append('\'');
        sb.append(", superString='").append(super.toString()).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
