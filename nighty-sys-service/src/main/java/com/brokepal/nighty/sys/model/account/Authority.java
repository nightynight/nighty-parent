package com.brokepal.nighty.sys.model.account;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by chenchao on 17/4/17.
 */
public class Authority implements Serializable {
    private String id;
    private String name;
    private String description;

    public Authority() {}

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Builder{
        private String id;
        private String name;
        private String description;

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
        public Authority build(){
            Authority authority = new Authority();
            if (this.id == null)
                authority.id = UUID.randomUUID().toString();
            else
                authority.id = this.id;
            authority.name = this.name;
            authority.description = this.description;
            return authority;
        }
    }
}
