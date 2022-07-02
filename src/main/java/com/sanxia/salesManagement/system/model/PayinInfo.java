package com.sanxia.salesManagement.system.model;

import java.math.BigDecimal;
import java.util.Date;

public class PayinInfo {
    private Integer id;

    private Date time;

    private BigDecimal payinMoney;

    private String origin;

    private Integer accountNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public BigDecimal getPayinMoney() {
        return payinMoney;
    }

    public void setPayinMoney(BigDecimal payinMoney) {
        this.payinMoney = payinMoney;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }
}