package com.brokepal.nighty.sys.model.account;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Created by chenchao on 17/4/17.
 */
public class Role implements Serializable {
    private String id;
    private String name;
    private String description;
    private String type;
    private List<Authority> authorities;

    public Role() {}

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class Builder{
        private String id;
        private String name;
        private String description;
        private String type;
        private List<Authority> authorities;

        public Builder id(String val){
            id = val;
            return this;
        }
        public Builder name(String val){
            name = val;
            return this;
        }
        public Builder description(String val){
            description = val;
            return this;
        }
        public Builder type(String val){
            type = val;
            return this;
        }
        public Builder authorities(List<Authority> val){
            authorities = val;
            return this;
        }
        public Role build(){
            Role role = new Role();
            if (this.id == null)
                role.id = UUID.randomUUID().toString();
            else
                role.id = this.id;
            role.name = this.name;
            role.description = this.description;
            role.type = this.type;
            role.authorities = this.authorities;
            return role;
        }
    }
}
