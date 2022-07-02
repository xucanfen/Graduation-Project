package com.sanxia.salesManagement.system.model;

import java.util.Date;

public class MemberInfo {
    private Integer vipId;

    private String vipName;

    private String vipTel;

    private Date vipBirthday;

    private Date joinTime;

    private String vipAddress;

    public Integer getVipId() {
        return vipId;
    }

    public void setVipId(Integer vipId) {
        this.vipId = vipId;
    }

    public String getVipName() {
        return vipName;
    }

    public void setVipName(String vipName) {
        this.vipName = vipName;
    }

    public String getVipTel() {
        return vipTel;
    }

    public void setVipTel(String vipTel) {
        this.vipTel = vipTel;
    }

    public Date getVipBirthday() {
        return vipBirthday;
    }

    public void setVipBirthday(Date vipBirthday) {
        this.vipBirthday = vipBirthday;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public String getVipAddress() {
        return vipAddress;
    }

    public void setVipAddress(String vipAddress) {
        this.vipAddress = vipAddress;
    }
}