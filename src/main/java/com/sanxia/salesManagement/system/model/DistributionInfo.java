package com.sanxia.salesManagement.system.model;

import java.util.Date;

public class DistributionInfo {
    private Integer distributionId;

    private Integer goodsId;

    private String goodsName;

    private Integer goodsNumber;

    private Date distributionTime;

    private String address;

    private String distributionName;

    private String distributionTel;

    public Integer getDistributionId() {
        return distributionId;
    }

    public void setDistributionId(Integer distributionId) {
        this.distributionId = distributionId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public Date getDistributionTime() {
        return distributionTime;
    }

    public void setDistributionTime(Date distributionTime) {
        this.distributionTime = distributionTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistributionName() {
        return distributionName;
    }

    public void setDistributionName(String distributionName) {
        this.distributionName = distributionName;
    }

    public String getDistributionTel() {
        return distributionTel;
    }

    public void setDistributionTel(String distributionTel) {
        this.distributionTel = distributionTel;
    }
}