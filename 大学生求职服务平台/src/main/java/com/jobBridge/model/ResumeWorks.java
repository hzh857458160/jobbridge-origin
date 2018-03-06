package com.jobBridge.model;

import java.util.Date;

/**
 * Created by SYunk on 2017/7/21.
 */
public class ResumeWorks {
    private Long resumeWorksId;
    private Long resumeId;
    private String company;
    private String city;
    private String province;
    private String jobName;
    private String projectName;
    private Date startDate;
    private Date endDate;
    private String sentence1;
    private String sentence2;
    private String sentence3;
    private String sentence4;

    public ResumeWorks(Long resumeWorksId, Long resumeId, String company, String city, String province, String jobName, String projectName,
                       Date startDate, Date endDate, String sentence1, String sentence2, String sentence3, String sentence4) {
        this.resumeWorksId = resumeWorksId;
        this.resumeId = resumeId;
        this.company = company;
        this.city = city;
        this.province = province;
        this.jobName = jobName;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sentence1 = sentence1;
        this.sentence2 = sentence2;
        this.sentence3 = sentence3;
        this.sentence4 = sentence4;
    }

    public ResumeWorks() {
    }

    public Long getResumeWorksId() {
        return resumeWorksId;
    }
    public Long getResumeId() {
        return resumeId;
    }
    public String getCompany() {
        return company;
    }
    public String getCity() {
        return city;
    }
    public String getProvince() {
        return province;
    }
    public String getJobName() {
        return jobName;
    }
    public String getProjectName() {
        return projectName;
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
    public String getSentence4() {
        return sentence4;
    }
    public void setResumeWorksId(Long resumeWorksId) {
        this.resumeWorksId = resumeWorksId;
    }
    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
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
    public void setSentence4(String sentence4) {
        this.sentence4 = sentence4;
    }
}
