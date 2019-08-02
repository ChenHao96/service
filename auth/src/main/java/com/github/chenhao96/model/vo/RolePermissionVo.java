package com.github.chenhao96.model.vo;

public class RolePermissionVo {

    private Integer id;

    private String roleName;

    private String urlName;

    private String url;

    private String updateAtStr;

    private String createAtStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        final StringBuilder sb = new StringBuilder("RolePermissionVo{");
        sb.append("id=").append(id);
        sb.append(", roleName='").append(roleName).append('\'');
        sb.append(", urlName='").append(urlName).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", updateAtStr='").append(updateAtStr).append('\'');
        sb.append(", createAtStr='").append(createAtStr).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
