package com.jobBridge.model;

/**
 * Created by SYunk on 2017/7/20.
 */


public class Resume {
    private Long resumeId;
    private Long studentId;

    public Resume(Long resumeId, Long studentId) {
        this.resumeId = resumeId;
        this.studentId = studentId;
    }

    public Resume() {
    }

    public Long getResumeId() {
        return resumeId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
