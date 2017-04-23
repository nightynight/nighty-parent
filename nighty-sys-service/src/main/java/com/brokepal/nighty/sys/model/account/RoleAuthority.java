package com.brokepal.nighty.sys.model.account;

import java.util.UUID;

/**
 * Created by chenchao on 17/4/17.
 */
public class RoleAuthority {
    private String id;
    private String roleId;
    private String authorityId;

    public RoleAuthority() {}

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

    public String getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(String authorityId) {
        this.authorityId = authorityId;
    }

    public static class Builder{
        private String id;
        private String roleId;
        private String authorityId;

        public Builder id(String val){
            id = val;
            return this;
        }
        public Builder roleId(String val){
            roleId = val;
            return this;
        }
        public Builder authorityId(String val){
            authorityId = val;
            return this;
        }
        public RoleAuthority build(){
            RoleAuthority roleAuthority = new RoleAuthority();
            if (this.id == null)
                roleAuthority.id = UUID.randomUUID().toString();
            else
                roleAuthority.id = this.id;
            roleAuthority.roleId = this.roleId;
            roleAuthority.authorityId = this.authorityId;
            return roleAuthority;
        }
    }
}
