package com.example.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Table(name = "`role`")
public class Role implements Serializable{
    @Id
    @Column(name = "`rid`")
    private Integer rid;

    @Column(name = "`rname`")
    private String rname;


    private Set<User> users = new HashSet<>();

    private Set<Module> modules = new HashSet<>();

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Module> getModules() {
        return modules;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

    /**
     * @return rid
     */
    public Integer getRid() {
        return rid;
    }

    /**
     * @param rid
     */
    public void setRid(Integer rid) {
        this.rid = rid;
    }

    /**
     * @return rname
     */
    public String getRname() {
        return rname;
    }

    /**
     * @param rname
     */
    public void setRname(String rname) {
        this.rname = rname == null ? null : rname.trim();
    }
}