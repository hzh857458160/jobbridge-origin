package com.jobBridge.model;

import java.sql.Timestamp;

/**
 * Created by GeniusHe on 2017/7/19.
 * 公司职位评论类
 */
public class Review {
    private Long enterpriseId;  //公司ID
    private Long studentId;     //学生ID
    private String jobName;     //工作名称
    private String content;     //评论内容
    private Timestamp dateTime; //评论时间

    public Review(Long enterpriseId, Long studentId, String jobName, String content, Timestamp dateTime) {
        this.enterpriseId = enterpriseId;
        this.studentId = studentId;
        this.jobName = jobName;
        this.content = content;
        this.dateTime = dateTime;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }
}
