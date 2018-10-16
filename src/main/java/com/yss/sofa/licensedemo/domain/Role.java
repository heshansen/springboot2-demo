package com.yss.sofa.licensedemo.domain;

import java.util.Date;

public class Role extends Base {

    private static final long serialVersionUID = 6982132372322827082L;
    /**
     * 角色名
     */
    private String name;

    private Date create_time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
