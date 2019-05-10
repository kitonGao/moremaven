package com.example.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Table(name = "`user`")
public class User implements Serializable{
    @Id
    @Column(name = "`uid`")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer uid;

    @Column(name = "`username`")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String username;

    @Column(name = "`password`")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<Role> roles = new HashSet<>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * @return uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}