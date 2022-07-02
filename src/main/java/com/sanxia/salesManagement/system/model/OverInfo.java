package com.sanxia.salesManagement.system.model;

import java.math.BigDecimal;
import java.util.Date;

public class OverInfo {
    private Integer id;

    private Integer salesmanId;

    private String salesmanName;

    private Date startTime;

    private Date endTime;

    private BigDecimal totalDays;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(BigDecimal totalDays) {
        this.totalDays = totalDays;
    }
}