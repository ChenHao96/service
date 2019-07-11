/**
 * Copyright 2019 ChenHao96
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.chenhao96.model;

import java.util.Date;

public class LoginUserInfo {

    private String userName;
    private String lastIp;
    private String currentIp;
    private Date lastLoginTime;
    private String lastLoginTimeStr;
    private Date currentLoginTime;
    private String currentLoginTimeStr;
    private String sessionId;
    private Integer status;//-1:封号 0:离线 1:在线 2:本机在线

    public LoginUserInfo() {
    }

    public LoginUserInfo(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public String getCurrentIp() {
        return currentIp;
    }

    public void setCurrentIp(String currentIp) {
        this.currentIp = currentIp;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getCurrentLoginTime() {
        return currentLoginTime;
    }

    public void setCurrentLoginTime(Date currentLoginTime) {
        this.currentLoginTime = currentLoginTime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLastLoginTimeStr() {
        return lastLoginTimeStr;
    }

    public void setLastLoginTimeStr(String lastLoginTimeStr) {
        this.lastLoginTimeStr = lastLoginTimeStr;
    }

    public String getCurrentLoginTimeStr() {
        return currentLoginTimeStr;
    }

    public void setCurrentLoginTimeStr(String currentLoginTimeStr) {
        this.currentLoginTimeStr = currentLoginTimeStr;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoginUserInfo{");
        sb.append("userName='").append(userName).append('\'');
        sb.append(", lastIp='").append(lastIp).append('\'');
        sb.append(", currentIp='").append(currentIp).append('\'');
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", lastLoginTimeStr='").append(lastLoginTimeStr).append('\'');
        sb.append(", currentLoginTime=").append(currentLoginTime);
        sb.append(", currentLoginTimeStr='").append(currentLoginTimeStr).append('\'');
        sb.append(", sessionId='").append(sessionId).append('\'');
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}