package com.jobBridge.model;

/**
 * Created by SYunk on 2017/7/19.
 */
public class Student {
    private Long studentId;
    private String userName;
    private String mailbox;
    private String password;

    public Student(Long studentId, String userName, String mailbox, String password) {
        this.studentId = studentId;
        this.userName = userName;
        this.mailbox = mailbox;
        this.password = password;
    }

    public Long getStudentId() {
        return studentId;
    }
    public String getUserName() {
        return userName;
    }
    public String getMailbox() {
        return mailbox;
    }
    public String getPassword() {
        return password;
    }
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
