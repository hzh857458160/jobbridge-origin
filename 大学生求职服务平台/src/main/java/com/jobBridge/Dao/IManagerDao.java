package com.jobBridge.Dao;

import com.jobBridge.model.Manager;

/**
 * Created by HanrAx on 2017/7/20.
 * 与数据库查询关联的接口
 */
public interface IManagerDao {
    Manager findManagerById(Integer managerId);     //通过管理员id获取管理员
    Manager findManagerByUserName(String userName); //通过管理员账号获取管理员
    void addManager(Manager manager);               //添加管理员账号

}
