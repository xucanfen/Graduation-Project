package com.sanxia.salesManagement.system.model;

import java.util.Date;

public class ChangeInfo {
    private Integer changeId;

    private Integer saleId;

    private Integer newGoodsId;

    private String newGoodsName;

    private String changeReason;

    private Date changeTime;

    private String applyStatus;

    public Integer getChangeId() {
        return changeId;
    }

    public void setChangeId(Integer changeId) {
        this.changeId = changeId;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public Integer getNewGoodsId() {
        return newGoodsId;
    }

    public void setNewGoodsId(Integer newGoodsId) {
        this.newGoodsId = newGoodsId;
    }

    public String getNewGoodsName() {
        return newGoodsName;
    }

    public void setNewGoodsName(String newGoodsName) {
        this.newGoodsName = newGoodsName;
    }

    public String getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }
}