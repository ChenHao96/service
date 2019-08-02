package com.github.chenhao96.model.bo;

public class AdminBo extends PageBo {

    private Integer id;

    private String photo;

    private String userName;

    private String nikeName;

    private String password;

    private String phoneNumber;

    private String changePassword;

    private String confirmPassword;

    private Integer updateAt;

    private Integer createAt;

    private String updateAtStr;

    private String createAtStr;

    private Integer roleNameId;

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

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Integer getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Integer updateAt) {
        this.updateAt = updateAt;
    }

    public String getChangePassword() {
        return changePassword;
    }

    public void setChangePassword(String changePassword) {
        this.changePassword = changePassword;
    }

    public Integer getRoleNameId() {
        return roleNameId;
    }

    public void setRoleNameId(Integer roleNameId) {
        this.roleNameId = roleNameId;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AdminBo{");
        sb.append("id=").append(id);
        sb.append(", photo='").append(photo).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", nikeName='").append(nikeName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", changePassword='").append(changePassword).append('\'');
        sb.append(", confirmPassword='").append(confirmPassword).append('\'');
        sb.append(", updateAt=").append(updateAt);
        sb.append(", createAt=").append(createAt);
        sb.append(", updateAtStr='").append(updateAtStr).append('\'');
        sb.append(", createAtStr='").append(createAtStr).append('\'');
        sb.append(", roleNameId=").append(roleNameId);
        sb.append(", superString='").append(super.toString()).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
