package com.jobBridge.model;

import java.util.Date;

/**
 * Created by SYunk on 2017/7/21.
 */
public class ResumeOrganization {
    private Long resumeOrganizationId;
    private Long resumeId;
    private String name;
    private String jobName;
    private Date startDate;
    private Date endDate;
    private String sentence1;
    private String sentence2;
    private String sentence3;

    public ResumeOrganization(Long resumeOrganizationId, Long resumeId, String name, String jobName, Date startDate, Date endDate,
                              String sentence1, String sentence2, String sentence3) {
        this.resumeOrganizationId = resumeOrganizationId;
        this.resumeId = resumeId;
        this.name = name;
        this.jobName = jobName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sentence1 = sentence1;
        this.sentence2 = sentence2;
        this.sentence3 = sentence3;
    }

    public ResumeOrganization() {
    }

    public Long getResumeOrganizationId() {
        return resumeOrganizationId;
    }
    public Long getResumeIdId() {
        return resumeId;
    }
    public String getName() {
        return name;
    }
    public String getJobName() {
        return jobName;
    }
    public Date getStartDate() {
        return startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public String getSentence1() {
        return sentence1;
    }
    public String getSentence2() {
        return sentence2;
    }
    public String getSentence3() {
        return sentence3;
    }
    public void setResumeOrganizationId(Long resumeOrganizationId) {
        this.resumeOrganizationId = resumeOrganizationId;
    }
    public void setResumeId(Long resuemId) {
        this.resumeId = resuemId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public void setSentence1(String sentence1) {
        this.sentence1 = sentence1;
    }
    public void setSentence2(String sentence2) {
        this.sentence2 = sentence2;
    }
    public void setSentence3(String sentence3) {
        this.sentence3 = sentence3;
    }
}
