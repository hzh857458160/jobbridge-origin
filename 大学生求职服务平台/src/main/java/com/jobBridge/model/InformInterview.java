package com.jobBridge.model;

import java.sql.Timestamp;

/**
 * Created by GeniusHan on 2017/7/19.
 *  面试通知类
 */
public class InformInterview {
    private Long informInterviewId;   //通知ID
    private Long enterpriseId;  //面试发出公司ID
    private Long studentId;     //面试学生ID
    private String content;     //通知内容
    private Timestamp dateTime; //通知时间

    public InformInterview(Long informInterviewId, Long enterpriseId, Long studentId, String content, Timestamp dateTime) {
        this.informInterviewId = informInterviewId;
        this.enterpriseId = enterpriseId;
        this.studentId = studentId;
        this.content = content;
        this.dateTime = dateTime;
    }

    public Long getInformInterviewId() {
        return informInterviewId;
    }

    public void setInformInterviewId(Long informInterviewId) {
        this.informInterviewId = informInterviewId;
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
