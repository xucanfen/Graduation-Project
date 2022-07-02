package com.sanxia.salesManagement.system.model;

import java.math.BigDecimal;
import java.util.Date;

public class StatisticsInfo {
    private Integer id;

    private Integer salesmanId;

    private String salesmanName;

    private Date statisticTime;

    private BigDecimal totalWork;

    private BigDecimal totalOver;

    private BigDecimal totalOff;

    private String attendance;

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

    public Date getStatisticTime() {
        return statisticTime;
    }

    public void setStatisticTime(Date statisticTime) {
        this.statisticTime = statisticTime;
    }

    public BigDecimal getTotalWork() {
        return totalWork;
    }

    public void setTotalWork(BigDecimal totalWork) {
        this.totalWork = totalWork;
    }

    public BigDecimal getTotalOver() {
        return totalOver;
    }

    public void setTotalOver(BigDecimal totalOver) {
        this.totalOver = totalOver;
    }

    public BigDecimal getTotalOff() {
        return totalOff;
    }

    public void setTotalOff(BigDecimal totalOff) {
        this.totalOff = totalOff;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }
}