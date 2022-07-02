package com.sanxia.salesManagement.system.model;

import java.sql.Time;
import java.util.Date;

public class WorkInfo {
    private Integer id;

    private Integer salesmanId;

    private String salesmanName;

    private Date time;

    private Time startTime;

    private Time endTime;

    private String late;

    private String leaveEarly;

    private String absenteeism;

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getLate() {
        return late;
    }

    public void setLate(String late) {
        this.late = late;
    }

    public String getLeaveEarly() {
        return leaveEarly;
    }

    public void setLeaveEarly(String leaveEarly) {
        this.leaveEarly = leaveEarly;
    }

    public String getAbsenteeism() {
        return absenteeism;
    }

    public void setAbsenteeism(String absenteeism) {
        this.absenteeism = absenteeism;
    }
}