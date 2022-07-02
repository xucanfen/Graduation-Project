package com.sanxia.salesManagement.system.model;

import java.util.Date;

public class SalesmanInfo {
    private Integer salesmanId;

    private String salesmanName;

    private String salesmanSex;

    private Integer salesmanAge;

    private String salesmanTel;

    private Date joinTime;

    public Integer getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Integer salesmanId) {
        this.salesmanId = salesmanId;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public String getSalesmanSex() {
        return salesmanSex;
    }

    public void setSalesmanSex(String salesmanSex) {
        this.salesmanSex = salesmanSex;
    }

    public Integer getSalesmanAge() {
        return salesmanAge;
    }

    public void setSalesmanAge(Integer salesmanAge) {
        this.salesmanAge = salesmanAge;
    }

    public String getSalesmanTel() {
        return salesmanTel;
    }

    public void setSalesmanTel(String salesmanTel) {
        this.salesmanTel = salesmanTel;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }
}