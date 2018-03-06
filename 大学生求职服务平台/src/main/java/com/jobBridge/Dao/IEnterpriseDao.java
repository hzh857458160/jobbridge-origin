package com.jobBridge.Dao;

import com.jobBridge.model.Enterprise;

/**
 * Created by SYunk on 2017/7/20.
 */
public interface IEnterpriseDao {
    Enterprise findEnterpriseById(Long enterpriseId); //根据公司号查询公司
    Enterprise findEnterpriseByUserName(String userName); //根据用户名查找公司
    Enterprise findEnterpriseByMailbox(String mailbox); //根据邮箱查找公司
    void addEnterprise(Enterprise enterprise);  //新增注册的公司账号
}
