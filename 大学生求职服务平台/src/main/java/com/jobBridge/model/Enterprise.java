package com.jobBridge.model;

/**
 * Created by SYunk on 2017/7/19.
 */
public class Enterprise {
    private Long enterpriseId;
    private String userName;
    private String name;
    private String mailbox;
    private String phoneNum;
    private String password;
    private String enterpriseIntroduction;
    private String iconAddress;

    public Enterprise(Long enterpriseId, String userName, String name, String mailbox, String phoneNum,
                      String password, String enterpriseIntroduction, String iconAddress) {
        this.enterpriseId = enterpriseId;
        this.userName = userName;
        this.name = name;
        this.mailbox = mailbox;
        this.phoneNum = phoneNum;
        this.password = password;
        this.enterpriseIntroduction = enterpriseIntroduction;
        this.iconAddress = iconAddress;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }
    public String getUserName() {
        return userName;
    }
    public String getName() {
        return name;
    }
    public String getMailbox() {
        return mailbox;
    }
    public String getPhoneNum() {
        return phoneNum;
    }
    public String getPassword() {
        return password;
    }
    public String getEnterpriseIntroduction() {
        return enterpriseIntroduction;
    }
    public String getIconAddress() {
        return iconAddress;
    }
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEnterpriseIntroduction(String enterpriseIntroduction) {
        this.enterpriseIntroduction = enterpriseIntroduction;
    }
    public void setIconAddress(String iconAddress) {
        this.iconAddress = iconAddress;
    }
}
