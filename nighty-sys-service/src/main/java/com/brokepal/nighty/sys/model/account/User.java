package com.brokepal.nighty.sys.model.account;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2016/11/3.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String nickname;
    private String username;
    private String password;
    private String salt;
    private String email;
    private String phone;
    private List<Role> roles;
    private List<Authority> authorities;
    private Date registerTime;//注册时间

    public User() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", registerTime=" + registerTime +
                '}';
    }

    public static class Builder{
        private String id;
        private String nickname;
        private String username;
        private String password;
        private String salt;
        private String email;
        private String phone;
        private List<Role> roles;
        private List<Authority> authorities;
        private Date registerTime;

        public Builder id(String val){
            id = val;
            return this;
        }
        public Builder nickname(String val){
            nickname = val;
            return this;
        }
        public Builder username(String val){
            username = val;
            return this;
        }
        public Builder password(String val){
            password = val;
            return this;
        }
        public Builder salt(String val){
            salt = val;
            return this;
        }
        public Builder email(String val){
            email = val;
            return this;
        }
        public Builder phone(String val){
            phone = val;
            return this;
        }
        public Builder roles(List<Role> val){
            roles = val;
            return this;
        }
        public Builder authorities(List<Authority> val){
            authorities = val;
            return this;
        }
        public Builder registerTime(Date val){
            registerTime = val;
            return this;
        }

        public User build(){
            User user = new User();
            if (this.id == null)
                user.id = UUID.randomUUID().toString();
            else
                user.id = this.id;
            user.nickname = this.nickname;
            user.username = this.username;
            user.password = this.password;
            user.salt = this.salt;
            user.email = this.email;
            user.phone = this.phone;
            user.roles = this.roles;
            user.authorities = this.authorities;
            user.registerTime = this.registerTime;
            return user;
        }
    }
}
