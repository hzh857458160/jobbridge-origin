package com.jobBridge.model;

import java.util.Date;

/**
 * Created by SYunk on 2017/7/21.
 */
public class ResumeEducation {
    private Long resumeEducationId;
    private Long resumeId;
    private String universityName;
    private String city;
    private String province;
    private String major;
    private Date graduateDate;
    private Integer grade;
    private String honors;
    private String relatedCourse;

    public ResumeEducation(Long resumeEducationId, Long resumeId, String universityName, String city,
                           String province, String major, Date graduateDate, Integer grade, String honors, String relatedCourse) {
        this.resumeEducationId = resumeEducationId;
        this.resumeId = resumeId;
        this.universityName = universityName;
        this.city = city;
        this.province = province;
        this.major = major;
        this.graduateDate = graduateDate;
        this.grade = grade;
        this.honors = honors;
        this.relatedCourse = relatedCourse;
    }

    public ResumeEducation() {
    }

    public Long getResumeEducationId() {
        return resumeEducationId;
    }
    public Long getResumeId() {
        return resumeId;
    }
    public String getUniversityName() {
        return universityName;
    }
    public String getCity() {
        return city;
    }
    public String getProvince() {
        return province;
    }
    public String getMajor() {
        return major;
    }
    public Date getGraduateDate() {
        return graduateDate;
    }
    public Integer getGrade() {
        return grade;
    }
    public String getHonors() {
        return honors;
    }
    public String getRelatedCourse() {
        return relatedCourse;
    }
    public void setResumeEducationId(Long resumeEducationId) {
        this.resumeEducationId = resumeEducationId;
    }
    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }
    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public void setMajor(String major) {
        this.major = major;
    }
    public void setGraduateDate(Date graduateDate) {
        this.graduateDate = graduateDate;
    }
    public void setGrade(Integer grade) {
        this.grade = grade;
    }
    public void setHonors(String honors) {
        this.honors = honors;
    }
    public void setRelatedCourse(String relatedCourse) {
        this.relatedCourse = relatedCourse;
    }
}
