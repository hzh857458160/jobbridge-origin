package com.jobBridge.model;

/**
 * Created by SYunk on 2017/7/21.
 */
public class ResumeHead {
    private Long resumeId;
    private String name;
    private String address;
    private String phoneNum;
    private String mailbox;

    public ResumeHead(Long resumeId, String name, String address, String phoneNum, String mailbox) {
        this.resumeId = resumeId;
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.mailbox = mailbox;
    }

    public ResumeHead() {
    }

    public Long getResumeId() {
        return resumeId;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getPhoneNum() {
        return phoneNum;
    }
    public String getMailbox() {
        return mailbox;
    }
    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }
}
