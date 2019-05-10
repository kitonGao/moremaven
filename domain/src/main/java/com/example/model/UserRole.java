package com.example.model;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "`user_role`")
public class UserRole implements Serializable{
    @Column(name = "`uid`")
    private Integer uid;

    @Column(name = "`rid`")
    private Integer rid;

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
}