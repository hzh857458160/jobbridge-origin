package com.jobBridge.model;

/**
 * Created by GeniusHe on 2017/7/19.
 */
public class Manager {
    private Integer managerId;  //管理员id
    private String userName;    //管理员用户名
    private String password;    //管理员密码

    public Manager(Integer managerId, String userName, String password) {
        this.managerId = managerId;
        this.userName = userName;
        this.password = password;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
