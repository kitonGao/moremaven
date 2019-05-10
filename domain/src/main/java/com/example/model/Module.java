package com.example.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Table(name = "`module`")
public class Module implements Serializable{
    @Id
    @Column(name = "`mid`")
    private Integer mid;

    @Column(name = "`mname`")
    private String mname;


    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * @return mid
     */
    public Integer getMid() {
        return mid;
    }

    /**
     * @param mid
     */
    public void setMid(Integer mid) {
        this.mid = mid;
    }

    /**
     * @return mname
     */
    public String getMname() {
        return mname;
    }

    /**
     * @param mname
     */
    public void setMname(String mname) {
        this.mname = mname == null ? null : mname.trim();
    }
}