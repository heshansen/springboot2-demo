package com.yss.sofa.licensedemo.domain;

public class UserRole extends Base {
    private static final long serialVersionUID = -8757175021643944153L;
    /**
     * 用户id
     */
    private Long userid;
    /**
     * 角色id
     */
    private Long roleid;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }
}
