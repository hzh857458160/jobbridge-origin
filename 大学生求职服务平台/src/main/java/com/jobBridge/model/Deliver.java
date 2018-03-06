package com.jobBridge.model;

import java.sql.Timestamp;

/**
 * Created by GeniusHe on 2017/7/20.
 * 简历投递类
 */
public class Deliver {
    private Long deliverId;
    private Long resumeId;      //学生对应简历Id
    private Long enterpriseId;  //被投简历的公司Id
    private Long recruitInfoId;         //招聘信息号
    private Timestamp dateTime; //投递日期时间
    private Boolean haveRead;
    private Boolean haveDelete;

    public Deliver(Long deliverId, Long resumeId, Long enterpriseId, Long recruitInfoId, Timestamp dateTime, Boolean haveRead, Boolean haveDelete) {
        this.deliverId = deliverId;
        this.resumeId = resumeId;
        this.enterpriseId = enterpriseId;
        this.recruitInfoId = recruitInfoId;
        this.dateTime = dateTime;
        this.haveRead = haveRead;
        this.haveDelete = haveDelete;
    }


    public Long getDeliverId() {
        return deliverId;
    }

    public Long getResumeId() {
        return resumeId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public Long getRecruitInfoId() {
        return recruitInfoId;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public Boolean getHaveRead() {
        return haveRead;
    }

    public Boolean getHaveDelete() {
        return haveDelete;
    }

    public void setDeliverId(Long deliverId) {
        this.deliverId = deliverId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public void setRecruitInfoId(Long recruitInfoId) {
        this.recruitInfoId = recruitInfoId;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public void setHaveRead(Boolean haveRead) {
        this.haveRead = haveRead;
    }

    public void setHaveDelete(Boolean haveDelete) {
        this.haveDelete = haveDelete;
    }
}
