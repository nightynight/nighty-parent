package com.brokepal.nighty.sys.web.dto;

import com.brokepal.nighty.sys.model.account.Role;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chenchao on 17/4/12.
 */
public class LoginSuccessResult implements Serializable {
    private static final long serialVersionUID = -201704122355000L;

    private String token;
    private String nickname;
    private List<Role> roles;

    public LoginSuccessResult() {}

    public LoginSuccessResult(String token, String nickname, List<Role> roles) {
        this.token = token;
        this.nickname = nickname;
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
