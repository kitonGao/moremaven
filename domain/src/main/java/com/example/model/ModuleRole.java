package com.example.model;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "`module_role`")
public class ModuleRole implements Serializable{
    @Column(name = "`rid`")
    private Integer rid;

    @Column(name = "`mid`")
    private Integer mid;

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
}