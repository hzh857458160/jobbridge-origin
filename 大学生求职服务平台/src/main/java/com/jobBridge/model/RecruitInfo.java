package com.jobBridge.model;

import java.sql.Timestamp;

/**
 * Created by SYunk on 2017/7/20.
 */
public class RecruitInfo {
    private Long recruitInfoId;
    private Long enterpriseId;
    private String jobName;
    private String jobDescribe;
    private String jobRequire;
    private String location;
    private Integer lowSalary;
    private Integer highSalary;
    private Timestamp dateTime;
    private String deadline;
    private Boolean haveDelete;

    public RecruitInfo(Long recruitInfoId, Long enterpriseId, String jobName, String jobDescribe, String jobRequire,
                       String location, Integer lowSalary, Integer highSalary, Timestamp dateTime, String deadline, Boolean haveDelete) {
        this.recruitInfoId = recruitInfoId;
        this.enterpriseId = enterpriseId;
        this.jobName = jobName;
        this.jobDescribe = jobDescribe;
        this.jobRequire = jobRequire;
        this.location = location;
        this.lowSalary = lowSalary;
        this.highSalary = highSalary;
        this.dateTime = dateTime;
        this.deadline = deadline;
        this.haveDelete = haveDelete;
    }

    public Long getRecruitInfoId() {
        return recruitInfoId;
    }
    public Long getEnterpriseId() {
        return enterpriseId;
    }
    public String getJobName() {
        return jobName;
    }
    public String getJobDescribe() {
        return jobDescribe;
    }
    public String getJobRequire() {
        return jobRequire;
    }
    public String getLocation() {
        return location;
    }
    public Integer getLowSalary() {
        return lowSalary;
    }
    public Integer getHighSalary() {
        return highSalary;
    }
    public Timestamp getDateTime() {
        return dateTime;
    }
    public String getDeadline() {
        return deadline;
    }
    public Boolean getHaveDelete() {
        return haveDelete;
    }

    public void setRecruitInfoId(Long recruitInfoId) {
        this.recruitInfoId = recruitInfoId;
    }
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
    public void setJobDescribe(String jobDescribe) {
        this.jobDescribe = jobDescribe;
    }
    public void setJobRequire(String jobRequire) {
        this.jobRequire = jobRequire;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setLowSalary(Integer lowSalary) {
        this.lowSalary = lowSalary;
    }
    public void setHighSalary(Integer highSalary) {
        this.highSalary = highSalary;
    }
    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
    public void setHaveDelete(Boolean haveDelete) {
        this.haveDelete = haveDelete;
    }
}
