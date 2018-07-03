package com.dome.springboot.mybatis.bean;

import java.util.Objects;

public class RoleVO {

    private int id;
    private String rolename;
    private int userid;

    public RoleVO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleVO)) return false;
        RoleVO roleVO = (RoleVO) o;
        return getId() == roleVO.getId() &&
                Objects.equals(getRolename(), roleVO.getRolename()) &&
                Objects.equals(getUserid(), roleVO.getUserid());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getRolename(), getUserid());
    }

    @Override
    public String toString() {
        return "RoleVO{" +
                "id=" + id +
                ", rolename='" + rolename + '\'' +
                ", userid='" + userid + '\'' +
                '}';
    }
}
