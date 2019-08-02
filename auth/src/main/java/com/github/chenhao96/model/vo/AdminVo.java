package com.github.chenhao96.model.vo;

import java.util.Set;

public class AdminVo {

    private Integer id;

    private String userName;

    private String nikeName;

    private String password;

    private String photo;

    private String phoneNumber;

    private Integer updateAt;

    private Integer createAt;

    private String updateAtStr;

    private String createAtStr;

    private Integer roleNameId;

    private String roleName;

    private boolean lock = false;

    private Set<String> urls;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public Integer getRoleNameId() {
        return roleNameId;
    }

    public void setRoleNameId(Integer roleNameId) {
        this.roleNameId = roleNameId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }

    public boolean getLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
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
        final StringBuilder sb = new StringBuilder("AdminVo{");
        sb.append("id=").append(id);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", nikeName='").append(nikeName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", photo='").append(photo).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", updateAt=").append(updateAt);
        sb.append(", createAt=").append(createAt);
        sb.append(", updateAtStr='").append(updateAtStr).append('\'');
        sb.append(", createAtStr='").append(createAtStr).append('\'');
        sb.append(", roleNameId=").append(roleNameId);
        sb.append(", roleName='").append(roleName).append('\'');
        sb.append(", lock=").append(lock);
        sb.append(", urls=").append(urls);
        sb.append('}');
        return sb.toString();
    }
}
