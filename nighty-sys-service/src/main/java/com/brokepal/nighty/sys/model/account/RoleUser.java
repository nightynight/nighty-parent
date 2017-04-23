package com.brokepal.nighty.sys.model.account;

import java.util.UUID;

/**
 * Created by chenchao on 17/4/17.
 */
public class RoleUser {
    private String id;
    private String roleId;
    private String userId;

    public RoleUser() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static class Builder{
        private String id;
        private String roleId;
        private String userId;

        public Builder id(String val){
            id = val;
            return this;
        }
        public Builder roleId(String val){
            roleId = val;
            return this;
        }
        public Builder userId(String val){
            userId = val;
            return this;
        }
        public RoleUser build(){
            RoleUser roleUser = new RoleUser();
            if (this.id == null)
                roleUser.id = UUID.randomUUID().toString();
            else
                roleUser.id = this.id;
            roleUser.roleId = this.roleId;
            roleUser.userId = this.userId;
            return roleUser;
        }
    }

}
